package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.SearchBean;
import org.tarena.note.entity.Share;

public interface ShareMapperDao {
	public Share findByNoteId(String noteId);
	public int save(Share share);
	/**
	 * ���ݱ���ģ����ѯ����ʼ���Ϣ
	 * @param title
	 * @return ���ط���ʼ�IDcn_share_id�ͱ���cn_share_title
	 */
	public List<Map<String,Object>> findLikeTitle(SearchBean bean);
}
