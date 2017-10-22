package org.tarena.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.SearchBean;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class SearchNotesController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/search")
	@ResponseBody
	public NoteResult execute(
			String title,int page){
		NoteResult result = 
			noteService.search(title,page);
		return result;
	}
	
}
