package com.dayuan.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dayuan.dto.AjaxDTO;
import com.dayuan.handler.HandlerMapping2;


//public class AtmServlet extends HttpServlet {
//
//	// AtmService atm = new AtmServiceImpl();
//	// UserService userService = new UserServiceImpl();
//	// BankCardService bankCardService = new BankCardServiceImpl();
//	private static final long serialVersionUID = -7137552077346522939L;
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doPost(req, resp);
//	}
//	
//	public void init(ServletConfig config) throws ServletException {
//		
////		try {
////			System.out.println("=====================init begin=========================");
////			
////			HandlerMapping2.init();
////			
////			System.out.println("=====================init end=========================");
////		} catch (Exception e) {
////			e.printStackTrace();
////			throw new ServletException(e.getMessage());
////		}
//		init();
//	}
//
//	
//
//	public static void main(String[] args) {
//		String uri = "/user/toUsercenter.do";
//		String[] uriArray = uri.split("\\/");
//		String tailURI = uriArray[uriArray.length - 1];
//		String clazzName = uriArray[uriArray.length - 2];
//		tailURI = tailURI.substring(0, tailURI.length() - 3);
//		System.out.println(tailURI);
//		System.out.println(clazzName);
//	}
//
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String uri = req.getRequestURI();
//		String url = req.getRequestURL().toString();
//		System.out.println("uri=" + uri);
//		System.out.println("url=" + url);
//
//		String cp = req.getServletContext().getContextPath();
//		System.out.println("cp=" + cp);// ��ȡ·��
//
//		String[] uriArray = uri.split("\\/");
//		String tailURI = uriArray[uriArray.length - 1];
//		String clazzName = uriArray[uriArray.length - 2];
//		String action = tailURI.substring(0, tailURI.length() - 3);
//
//		System.out.println("action=" + action);
//
//		Object targetObj = HandlerMapping2.getController(clazzName);
//		System.out.println("targetObj="+targetObj);
//		if (null == targetObj) {
//			System.out.println(">>>>>>>>> URL erro");
//			return;
//		}
//		try {
//			Method method = targetObj.getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
//			Object result = method.invoke(targetObj, req, resp);
//
//			if (null == result) {
//				return;
//			}
//
//			if (result instanceof String) {
//				String pagename = "/WEB-INF/page/" + result.toString() + ".jsp";
//				req.getRequestDispatcher(pagename).forward(req, resp);
//			}
//
//			if (result instanceof AjaxDTO) {
//				resp.setContentType("text/html;charset=utf-8");
//				resp.setCharacterEncoding("utf-8");
//				try (OutputStream os = resp.getOutputStream()) {
//					os.write(JSON.toJSONString(result).getBytes("utf-8"));
//					os.flush();
//				}
//			}
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}

// @Override
// protected void doPost(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// // TODO Auto-generated method stub
// String action = req.getParameter("action");// ���ò���
// System.out.println("action=" + action);
//
// if ("toPage".equals(action)) {
// toPage(req, resp);
// }
//
// if ("openAccount".equals(action)) {
// openAccount(req, resp);
// }
//
// if ("deposit".equals(action)) {
// deposit(req, resp);
// }
//
// if ("draw".equals(action)) {
// draw(req, resp);
// }
//
// if ("transfer".equals(action)) {
// transfer(req, resp);
// }
//
// if ("flow".equals(action)) {
// flow(req, resp);
// }
//
// if ("regist".equals(action)) {
// regist(req, resp);
// }
//
// if ("login".equals(action)) {
// login(req, resp);
// }
//
// if ("toFlow".equals(action)) {
// toFlow(req, resp);
// }
//
// if ("toDeposit".equals(action)) {
// toDeposit(req, resp);
// }
//
// if ("toDraw".equals(action)) {
// toDraw(req, resp);
// }
//
// if ("toTransfer".equals(action)) {
// toTransfer(req, resp);
// }
//
// if ("deleteCard".equals(action)) {
// deleteCard(req, resp);
// }
//
// if ("toUsercenter".equals(action)) {
// toUserCenter(req, resp);
// }
//
// if ("upload".equals(action)) {
// upload(req, resp);
// }
//
// if ("showPic".equals(action)) {
// showPic(req, resp);// ��ʾͼƬ��action
// }
//
// if ("downFlow".equals(action)) {
// downFlow(req, resp);
// }
//
// if ("loadBankCard".equals(action)) {
// loadBankCard(req, resp);
// }
// }
//
// if ("regist".equals(action)) {
// regist(req, resp);
// }
//
//
//
// if ("login".equals(action)) {
// login(req, resp);
// }
//
// }
//
// private void toPage(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// HttpSession session = req.getSession();
// String pageName = req.getParameter("pageName");
// req.getRequestDispatcher("/WEB-INF/page/" + pageName + ".jsp").forward(req,
// resp);
// }
//
// private void toOpenAccount(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// // String openAccount = req.getParameter("openAccount");// ���ò���
// req.getRequestDispatcher("/WEB-INF/page/openAccount.jsp").forward(req, resp);
// }
//
// private void openAccount(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// String passWord = req.getParameter("passWord");
//
// HttpSession session = req.getSession();
// User user = (User) session.getAttribute("user");
//
// BankCard bc = atm.openAccount(passWord, user.getId());
// req.setAttribute("bankCardAtm", bc);
//
// req.getRequestDispatcher("/WEB-INF/page/bankCardInfo.jsp").forward(req,
// resp);
// }
//
// private void toDeposit(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// String cardNumber = req.getParameter("cardNumber");
// req.setAttribute("cardNumber", cardNumber);
// req.getRequestDispatcher("/WEB-INF/page/deposit.jsp").forward(req, resp);
// // // String deposit = req.getParameter("deposit");
// // req.getRequestDispatcher("/WEB-INF/page/deposit.jsp").forward(req, resp);
// }
//
// private void deposit(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// String cardNumber = req.getParameter("cardNumber");
// System.out.println(cardNumber);
// String passWord = req.getParameter("passWord");
// String sum = req.getParameter("sum");
// atm.deposit(sum, cardNumber, passWord);
// BankCard bankCard = atm.getBankCard(cardNumber);
// req.setAttribute("bankCardAtm", bankCard);
// req.getRequestDispatcher("/WEB-INF/page/bankCardInfo.jsp").forward(req,
// resp);
//
// }
//
// private void toDraw(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// String cardNumber = req.getParameter("cardNumber");
// req.setAttribute("cardNumber", cardNumber);
// req.getRequestDispatcher("/WEB-INF/page/draw.jsp").forward(req, resp);
//
// }
//
// private void draw(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// String cardNumber = req.getParameter("cardNumber");
// System.out.println(cardNumber);
// String passWord = req.getParameter("passWord");
// String sum = req.getParameter("sum");
// atm.draw(sum, cardNumber, passWord);
// BankCard bankCard = atm.getBankCard(cardNumber);
// req.setAttribute("bankCardAtm", bankCard);
// req.getRequestDispatcher("/WEB-INF/page/bankCardInfo.jsp").forward(req,
// resp);
// }
//
// private void toTransfer(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// String cardNumber = req.getParameter("cardNumber");
// req.setAttribute("cardNumber", cardNumber);
// req.getRequestDispatcher("/WEB-INF/page/transfer.jsp").forward(req, resp);
// }
//
// private void transfer(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// String outCardNum = req.getParameter("outCardNum");
// System.out.println(outCardNum);
// String passWord = req.getParameter("passWord");
// String inCardNum = req.getParameter("inCardNum");
// String sum = req.getParameter("sum");
// atm.transfer(sum, inCardNum, outCardNum, passWord);
// BankCard bankCard = atm.getBankCard(outCardNum);
// req.setAttribute("bankCardAtm", bankCard);
// req.getRequestDispatcher("/WEB-INF/page/bankCardInfo.jsp").forward(req,
// resp);
// }
//
// private void toFlow(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// // String cardNumber =req.getParameter("cardNumber");
// // String passWord = req.getParameter("passWord");
// // List<Flow> list = atm.queryFlow(cardNumber, passWord);
// // req.setAttribute("flowInfo", list);
// // req.setAttribute("cardNumber", cardNumber);
// // req.setAttribute("passWord", passWord);
// String cardNumber = req.getParameter("cardNumber");
// req.setAttribute("cardNumber", cardNumber);
// req.getRequestDispatcher("/WEB-INF/page/flow.jsp").forward(req, resp);
//
// }
//
// private void flow(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// String cardNumber = req.getParameter("cardNumber");
// String passWord = req.getParameter("passWord");
// String currentPage = req.getParameter("currentPage");
// System.out.println("currentPage=" + currentPage);
//
// resp.setContentType("text/html;charset=utf-8");
// resp.setCharacterEncoding("utf-8");
//
// OutputStream os = resp.getOutputStream();
//
// try {
//
// PageHolder ph = atm.queryFlow(cardNumber, passWord,
// Integer.parseInt(currentPage));
// os.write(AjaxDTO.success(ph).getBytes("utf-8"));
// os.flush();
// } catch(Exception e) {
// os.write(AjaxDTO.faild(e.getMessage()).getBytes("utf-8"));
// os.flush();
// }
//
// }
//
//
// private void toregist(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
//
// req.getRequestDispatcher("/WEB-INF/page/regist.jsp").forward(req, resp);
// }
//
// private void regist(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// String username = req.getParameter("username");
// String passWord = req.getParameter("passWord");
//
// OutputStream os = resp.getOutputStream();// ��ȡ�����
// try {
// User user = userService.regist(username, passWord);
// os.write(AjaxDTO.success().getBytes("utf-8"));
//
// } catch (BizException be) {
// os.write(AjaxDTO.faild(be.getMessage()).getBytes("utf-8"));
//
// } catch (Exception e) {
// os.write(AjaxDTO.faild("ϵͳ�쳣������ϵ�ͷ�").getBytes("utf-8"));
//
// }
//
// }
//
// private void tologin(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
//
// req.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(req, resp);
// }
//
// private void login(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// String username = req.getParameter("username");
// String passWord = req.getParameter("passWord");
//
// resp.setContentType("text/html;charset=utf-8");// ������������
// resp.setCharacterEncoding("utf-8");// �����ַ�����
//
// OutputStream os = resp.getOutputStream();// ��ȡ�����
// try {
// User user = userService.login(username, passWord);// ��¼����
// HttpSession session = req.getSession();
// session.setAttribute("user", user);
//
// os.write(AjaxDTO.success().getBytes("utf-8"));
// os.flush();
//
// } catch (BizException be) {
// os.write(AjaxDTO.faild(be.getMessage()).getBytes("utf-8"));
// os.flush();
// return;
// } finally {
// os.close();
// }
//
// }
//
// private void toUserCenter(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
//
//
// req.getRequestDispatcher("/WEB-INF/page/usercenter.jsp").forward(req, resp);
// }
//
// private void showPic(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// String rp = req.getServletContext().getRealPath("/");// �õ�����·��
//
// HttpSession session = req.getSession();// ��ûỰ
// User user = (User) session.getAttribute("user");// �������
//
// try (FileInputStream fis = new FileInputStream(rp + "WEB-INF/upload/" +
// user.getId());
// OutputStream os = resp.getOutputStream()) {
// byte[] data = new byte[1024];
// int length = -1;
// while (-1 != (length = fis.read(data))) {
// os.write(data, 0, length);
// os.flush();
// }
// }
// }
//
// private void deleteCard(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// String cardId = req.getParameter("cardId");
// bankCardService.deleteCard(Integer.parseInt(cardId));
//
// toUserCenter(req, resp);
//
// }
//
// private void upload(HttpServletRequest req, HttpServletResponse resp) throws
// ServletException, IOException {
// DiskFileItemFactory factory = new DiskFileItemFactory();
// factory.setSizeThreshold(1024);// �ϴ�1024
// String rp = req.getServletContext().getRealPath("/");
// factory.setRepository(new File(rp + "WEB-INF/upload-tmp"));// ������ʱ�����
//
// ServletFileUpload upload = new ServletFileUpload(factory);// Servlet�ļ��ϴ�
// upload.setSizeMax(1024 * 230);// ���óߴ����
//
// try {
// List<FileItem> items = upload.parseRequest(req);
// Iterator<FileItem> iter = items.iterator();
// while (iter.hasNext()) {
// FileItem item = iter.next();
//
// if (item.isFormField()) {
// String name = item.getFieldName();
// String value = item.getString();
// System.out.println("[" + name + "=" + value + "]");
// } else {
// HttpSession session = req.getSession();
// User user = (User) session.getAttribute("user");
// // String fileName = "" + user.getId();
// String fileName = String.valueOf(user.getId());// ��ȡ�û���id
//
// File uploadedFile = new File(rp + "WEB-INF/upload/" + fileName);// �ϴ����ļ�
// item.write(uploadedFile);// д�����ͼƬ
// }
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
//
// toUserCenter(req, resp);
// }
//
// private void downFlow(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// String cardNumber = req.getParameter("cardNumber");
// String passWord = req.getParameter("passWord");
// System.out.println("cardNumber=" + cardNumber);
// System.out.println("passWord=" + passWord);
// String filename = cardNumber + ".csv";
//
// resp.setContentType("application/octet-stream");
// resp.setHeader("Content-Disposition", "attachment;filename=" + filename);
//
// try (BufferedWriter bw = new BufferedWriter(new
// OutputStreamWriter(resp.getOutputStream()))) {
// int currentPage = 1;
//
// String header = "ID,����,���,��ע";
// bw.write(header);
// bw.newLine();
// bw.flush();
// System.out.println("w");
// while (true) {
// PageHolder ph = atm.queryFlow(cardNumber, passWord, currentPage);
// Object obj = ph.getObj();
// if (null == obj) {
// break;
// }
//
// List<Flow> flowList = (List<Flow>) obj;
// if (flowList.isEmpty()) {
// break;
// }
//
// for (Flow flow : flowList) {
// StringBuilder sx = new StringBuilder();
// sx.append(flow.getId()).append(",").append(flow.getCardNum()).append(",").append(flow.getAmount())
// .append(",").append(flow.getDescript()).append(",");
//
// bw.write(sx.toString());
// bw.newLine();
// bw.flush();
//
// }
//
// currentPage++;
//
// }
// }
//
// }
//
// public void loadBankCard(HttpServletRequest req, HttpServletResponse resp)
// throws ServletException, IOException {
// HttpSession session = req.getSession();
// User user = (User)session.getAttribute("user");
//
// String currentPage = req.getParameter("currentPage");
// currentPage = currentPage == null ? "1" : currentPage;
//
// // ��ѯ���п�
// PageHolder pageHolder = bankCardService.listBankCard(user.getId(),
// Integer.parseInt(currentPage));
//
// OutputStream os = resp.getOutputStream();
// os.write(AjaxDTO.success(pageHolder).getBytes());
// os.flush();
// }
// }
