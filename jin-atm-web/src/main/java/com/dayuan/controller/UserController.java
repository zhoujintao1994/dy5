package com.dayuan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.dayuan.domain.User;
import com.dayuan.dto.AjaxDTO;
import com.dayuan.exception.BizException;
import com.dayuan.serviceimpl.RedisService;
import com.dayuan.serviceimpl.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisService redisService;

	static Logger log = LoggerFactory.getLogger(UserController.class);
//	@RequestMapping("/showPic")
//	public void showPic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String rp = req.getServletContext().getRealPath("/");// 閿熺煫纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹璺敓鏂ゆ嫹
//
//		HttpSession session = req.getSession();// 閿熸枻鎷锋病宄勶拷
//		User user = (User) session.getAttribute("user");// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
//
//		String filepath = rp + "\\WEB-INF\\upload\\" + user.getId();
//
//		System.out.println(">>>>>>>>>>filepath=" + filepath);
//		try (FileInputStream fis = new FileInputStream(filepath); OutputStream os = resp.getOutputStream()) {
//			byte[] data = new byte[1024];
//			int length = -1;
//			while (-1 != (length = fis.read(data))) {
//				os.write(data, 0, length);
//				os.flush();
//			}
//		}
//	}
	
	
		@RequestMapping("/showPic")
		public void showPic(HttpSession session, HttpServletResponse resp) throws ServletException, IOException {
	   	    String filepath = WebUtils.getRealPath(session.getServletContext(), "/upload/" + getUserId(session));
			
			try (FileInputStream fis = new FileInputStream(filepath);
					OutputStream os = resp.getOutputStream()) {
				byte[] data = new byte[1024];
				int length = -1;
				while (-1 != (length = fis.read(data))) {
					os.write(data, 0, length);
					os.flush();
				}
			}
		}

	@RequestMapping("/upload2")
	public String upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024);// 閿熻緝杈炬嫹1024
		String rp = req.getServletContext().getRealPath("/");
		System.out.println("rp=" + rp);
		factory.setRepository(new File(rp + "/WEB-INF/upload-tmp"));// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋椂閿熸枻鎷烽敓鏂ゆ嫹閿燂拷

		ServletFileUpload upload = new ServletFileUpload(factory);// Servlet閿熶茎纭锋嫹閿熻緝杈炬嫹
		upload.setSizeMax(4194304);// 閿熸枻鎷烽敓鐭昂杈炬嫹閿熸枻鎷烽敓锟�

		try {
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					System.out.println("[" + name + "=" + value + "]");
				} else {
					HttpSession session = req.getSession();
					User user = (User) session.getAttribute("user");
					// String fileName = "" + user.getId();
					String fileName = String.valueOf(user.getId());// 閿熸枻鎷峰彇閿熺煫浼欐嫹閿熸枻鎷穒d

					File uploadedFile = new File(rp + "/WEB-INF/upload/" + fileName);// 閿熻緝杈炬嫹閿熸枻鎷烽敓渚ョ》鎷�
					item.write(uploadedFile);// 鍐欓敓鏂ゆ嫹閿熸枻鎷烽敓閰佃锟�
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toUserCenter(req, resp);
	}

	// 推荐
	@RequestMapping("/upload")
	public void upload(MultipartFile upfile, HttpSession session, HttpServletResponse respon) {

		try {
			if (!upfile.isEmpty()) {
				String filePath = WebUtils.getRealPath(session.getServletContext(), "/upload/" + getUserId(session));
				File uploadedFile = new File(filePath);
				upfile.transferTo(uploadedFile);

				OutputStream os = respon.getOutputStream();
				os.write("<script type=\"text/javascript\">parent.loadAvatar();</script>".getBytes());
				os.flush();
				os.close();																														
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public AjaxDTO login(HttpServletRequest req, HttpServletResponse resp) throws
	// ServletException, IOException {
	// String username = req.getParameter("username");
	// String password = req.getParameter("password");
	//
	// resp.setContentType("text/html;charset=utf-8");
	// resp.setCharacterEncoding("utf-8");
	//
	// try {
	// User user = userService.login(username, password);
	// HttpSession session = req.getSession();
	// session.setAttribute("user", user);
	//
	// return AjaxDTO.success();
	// } catch (BizException be) {
	// return AjaxDTO.faild(be.getMessage());
	// }
	//
	// }

	@RequestMapping("/login")
	@ResponseBody
	public AjaxDTO login(String username, String passWord, String code, HttpServletRequest req) {
				//不能为"" 或null
		if (StringUtils.isBlank(code)) {
			return AjaxDTO.faild("验证码错误");
		}
		
		code = code.trim();//忽略字符左右两边的空格
		
		Object obj = req.getSession().getAttribute("capcode");
		if (null == obj) {
			return AjaxDTO.faild("验证码错误");
		}
		
		String realCode = (String)obj;
			
		//不区分大小写比较
		if (!realCode.equalsIgnoreCase(code)) {
			return AjaxDTO.faild("验证码错误");
		}
		
		User user = userService.login(username, passWord);
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		
		return AjaxDTO.success();
		
	}
	
	@RequestMapping("/toLoad")
	public String toLoad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "load";
	}
	

	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "login";
	}
	
	@RequestMapping("/toOpenAccount")
	public String toOpenAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "openAccount";
	}

//	@RequestMapping("/regist")
//	@ResponseBody
//	public AjaxDTO regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String username = req.getParameter("username");
//		String password = req.getParameter("passWord");
//
//		OutputStream os = resp.getOutputStream();
//
//		try {
//			User user = userService.regist(username, password);
//			return AjaxDTO.success();
//
//		} catch (BizException be) {
//			return AjaxDTO.faild(be.getMessage());
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			return AjaxDTO.faild("注册失败，请联系客服");
//		}
//
//	}
	
	@RequestMapping("/toRegist")
	public String toRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = UUID.randomUUID().toString();//UUID是由一个十六位的数字组成,表现出来的形式的唯一码 
		req.getSession().setAttribute("registToken", token);
		return "regist";
	}
	
	@RequestMapping("/regist")
	@ResponseBody
	public AjaxDTO regist(String username, String passWord, String registToken, HttpSession session) {
		if(StringUtils.isBlank(registToken)) {
			return AjaxDTO.faild("非法请求");
		}
		
		Object obj = session.getAttribute("registToken");
		if(null == obj) {
			return AjaxDTO.faild("请刷新页面，重新注册1");
		}
		
		if(!registToken.equals(obj.toString()) ) {
			return AjaxDTO.faild("请刷新页面，重新注册");
		}
		
		System.out.println(">>>>." + username);
		
		userService.regist(username, passWord);
		session.removeAttribute(registToken);
		return AjaxDTO.success();
		
	}
	
	@RequestMapping("/regist2")
	@ResponseBody
	public AjaxDTO regist2(String username, String password, String registToken, HttpSession session) {
		
		String key = "regist:" + username;//获得key
		boolean flag = redisService.setNX(key, 1);
		if (!flag) {
			return AjaxDTO.faild("请求太频繁");
		}
		
		userService.regist(username, password);
		
		return AjaxDTO.success();
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();//废掉session
		return "redirect:/user/toLogin.do";//重定向
	}
	
}
