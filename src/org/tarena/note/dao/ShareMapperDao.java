package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.SearchBean;
import org.tarena.note.entity.Share;

public interface ShareMapperDao {
	public Share findByNoteId(String noteId);
	public int save(Share share);
	/**
	 * 根据标题模糊查询分享笔记信息
	 * @param title
	 * @return 返回分享笔记IDcn_share_id和标题cn_share_title
	 */
	public List<Map<String,Object>> findLikeTitle(SearchBean bean);
}
