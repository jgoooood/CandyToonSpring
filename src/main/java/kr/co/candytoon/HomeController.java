package kr.co.candytoon;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.candytoon.webtoon.domain.Webtoon;
import kr.co.candytoon.webtoon.service.WebtoonService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private WebtoonService wService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home.kr", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		List<Webtoon> topViewList = wService.selectListByViewCount();
		model.addAttribute("topViewList", topViewList);
		
		List<Webtoon> topNewList = wService.selectListByNewOpen();
		model.addAttribute("topNewList", topNewList);
		
		List<Webtoon> topEndList = wService.selectListByEnd();
		model.addAttribute("topEndList", topEndList);
		
		return "home";
	}
	
}
