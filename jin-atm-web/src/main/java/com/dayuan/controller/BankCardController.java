package com.dayuan.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuan.domain.BankCard;
import com.dayuan.domain.Flow;
import com.dayuan.domain.PageHolder;
import com.dayuan.domain.User;
import com.dayuan.dto.AjaxDTO;
import com.dayuan.serviceimpl.AtmService;
import com.dayuan.serviceimpl.BankCardService;
import com.dayuan.utill.TestLogback;

@Controller
@RequestMapping("/card")
public class BankCardController extends BaseController {
	
	@Autowired
	private AtmService atmService;

	@Autowired
	private BankCardService bankCardService;
	
	private Logger log = LoggerFactory.getLogger(BankCardController.class);

	// public void setAtmService(AtmService atmService) {
	// this.atmService = atmService;
	// }
	//
	// public void setBankCardService(BankCardService bankCardService) {
	// this.bankCardService = bankCardService;
	// }
	@RequestMapping("/openAccount")
	@ResponseBody
	public AjaxDTO openAccount(String passWord, HttpSession session) {

		if (StringUtils.isBlank(passWord)) {
			return AjaxDTO.faild("密码不能为空");
		}

		BankCard bc = atmService.openAccount(passWord, getUserId(session));

		return AjaxDTO.success(bc);
	}

	// @RequestMapping("/toPage")
	// public String toPage(HttpServletRequest req, HttpServletResponse resp) throws
	// ServletException, IOException {
	// HttpSession session = req.getSession();
	// System.out.println(session);
	// String pageName = req.getParameter("pageName");
	// return req.getParameter("pageName");
	// }

	// @RequestMapping("/toDeposit")
	// public String toDeposit(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	// String cardNumber = req.getParameter("cardNumber");
	// req.setAttribute("cardNumber", cardNumber);
	// req.getRequestDispatcher("/WEB-INF/page/deposit.jsp").forward(req, resp);
	// // // String deposit = req.getParameter("deposit");
	// // req.getRequestDispatcher("/WEB-INF/page/deposit.jsp").forward(req, resp);
	// return "deposit";
	// }

	// @RequestMapping("/toDeposit")
	// public String toDeposit(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	// String cardNumber = req.getParameter("cardNumber");
	// req.setAttribute("cardNumber", cardNumber);
	// req.getRequestDispatcher("/WEB-INF/page/deposit.jsp").forward(req, resp);
	// // // String deposit = req.getParameter("deposit");
	// // req.getRequestDispatcher("/WEB-INF/page/deposit.jsp").forward(req, resp);
	// return "deposit";
	// }

	@RequestMapping("/deposit")
	@ResponseBody
	public AjaxDTO deposit(String sum, String cardNumber, String passWord) {
		System.out.println("111,1,1,"+sum+ cardNumber+passWord);
		atmService.deposit(sum, cardNumber, passWord);
		BankCard bankCard = atmService.getBankCard(cardNumber);

		return AjaxDTO.success(bankCard);

	}
	
	@RequestMapping("/toDeposit")
	public String toDeposit() {
		return "deposit";
	}

	@RequestMapping("/toDraw")
	public String toDraw(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		return "draw";

	}

//	@RequestMapping("/draw")
//	public String draw(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String cardNumber = req.getParameter("cardNumber");
//		String passWord = req.getParameter("passWord");
//		String sum = req.getParameter("sum");
//		atmService.draw(sum, cardNumber, passWord);
//		BankCard bankCard = atmService.getBankCard(cardNumber);
//		req.setAttribute("bankCardAtm", bankCard);
//		return "bankCardInfo";
//	}
	
	@RequestMapping("/draw")
	@ResponseBody
	public AjaxDTO draw(String cardNumber, String passWord, String sum ){
		
		atmService.draw(sum, cardNumber, passWord);
		BankCard bankCard = atmService.getBankCard(cardNumber);

		return AjaxDTO.success(bankCard);
	}

	@RequestMapping("/toTransfer")
	public String toTransfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cardNumber = req.getParameter("cardNumber");
		req.setAttribute("cardNumber", cardNumber);
		return "transfer";
	}

//	@RequestMapping("/transfer")
//	public String transfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String outCardNum = req.getParameter("outCardNum");
//		System.out.println(outCardNum);
//		String passWord = req.getParameter("passWord");
//		String inCardNum = req.getParameter("inCardNum");
//		String sum = req.getParameter("sum");
//		atmService.transfer(sum, inCardNum, outCardNum, passWord);
//		BankCard bankCard = atmService.getBankCard(outCardNum);
//		req.setAttribute("bankCardAtm", bankCard);
//		return "bankCardInfo";
//	}
	
	@RequestMapping("/transfer")
	@ResponseBody
	public AjaxDTO transfer(String sum, String inCardNum, String outCardNum, String passWord) {
		BankCard bankCard = atmService.getBankCard(outCardNum);
		atmService.outTransfer(sum, inCardNum, outCardNum, passWord);
		return AjaxDTO.success(bankCard);
	}

	@RequestMapping("/toFlow")
	public String toFlow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String cardNumber =req.getParameter("cardNumber");
		// String passWord = req.getParameter("passWord");
		// List<Flow> list = atm.queryFlow(cardNumber, passWord);
		// req.setAttribute("flowInfo", list);
		// req.setAttribute("cardNumber", cardNumber);
		// req.setAttribute("passWord", passWord);
		String cardNumber = req.getParameter("cardNumber");
		req.setAttribute("cardNumber", cardNumber);
		return "flow";

	}

	@RequestMapping("/flow")
	@ResponseBody
	public AjaxDTO flow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentPage = req.getParameter("currentPage");
		String cardNumber = req.getParameter("cardNumber");
		String passWord = req.getParameter("passWord");
		
		System.out.println("currentPage=" + currentPage);

		try {
			
			System.out.println("11");
			PageHolder ph = atmService.queryFlow(cardNumber, passWord, Integer.parseInt(currentPage));
			return AjaxDTO.success(ph);
		} catch (Exception e) {
			log.error("11111111",e);
			return AjaxDTO.faild(e.getMessage());

		}

	}

	@RequestMapping("/deleteCard")
	public String deleteCard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cardId = req.getParameter("cardId");
		bankCardService.deleteCard(Integer.parseInt(cardId));

		return toUserCenter(req, resp);

	}

	@RequestMapping("/downFlow")
	public void downFlow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cardNumber = req.getParameter("cardNumber");
		String passWord = req.getParameter("passWord");
		System.out.println("cardNumber=" + cardNumber);
		System.out.println("passWord=" + passWord);
		String filename = cardNumber + ".csv";

		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment;filename=" + filename);

		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()))) {
			int currentPage = 1;

			String header = "ID,卡号,金额,备注";
			bw.write(header);
			bw.newLine();
			bw.flush();
			System.out.println("w");
			while (true) {
				PageHolder ph = atmService.queryFlow(cardNumber, passWord, currentPage);
				Object obj = ph.getObj();
				if (null == obj) {
					break;
				}

				List<Flow> flowList = (List<Flow>) obj;
				if (flowList.isEmpty()) {
					break;
				}

				for (Flow flow : flowList) {
					StringBuilder sx = new StringBuilder();
					sx.append(flow.getId()).append(",").append(flow.getCardNum()).append(",").append(flow.getAmount())
							.append(",").append(flow.getDescript()).append(",");

					bw.write(sx.toString());
					bw.newLine();
					bw.flush();

				}

				currentPage++;

			}
		}

	}

	@RequestMapping("/loadBankCard")
	@ResponseBody
	public AjaxDTO loadBankCard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		String currentPage = req.getParameter("currentPage");
		currentPage = currentPage == null ? "1" : currentPage;

		// ��ѯ���п�
		PageHolder pageHolder = bankCardService.listBankCard(user.getId(), Integer.parseInt(currentPage));

		return AjaxDTO.success(pageHolder);
	}

	@RequestMapping("/loadBankCardNoPage")
	@ResponseBody
	public AjaxDTO loadBankCard(HttpSession session) {
		List<BankCard> bankCardList = bankCardService.listBankCard(getUserId(session));
		return AjaxDTO.success(bankCardList);
	}

	@RequestMapping("/loadFLowTen")
	@ResponseBody
	public AjaxDTO loadFLowTen(HttpSession session) {
		try {
		
			return AjaxDTO.success(bankCardService.listFLow(getUserId(session)));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return AjaxDTO.faild(e.getMessage());
		}
	}
}
