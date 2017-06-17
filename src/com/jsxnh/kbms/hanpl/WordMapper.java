package com.jsxnh.kbms.hanpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.jsxnh.kbms.hanpl.Word;
public class WordMapper implements RowMapper<Word>{

	public Word mapRow(ResultSet arg0, int arg1) throws SQLException {
		Word word=new Word();
		word.setId(arg0.getInt("id"));
		word.setWord(arg0.getString("word"));
		word.setNature(arg0.getString("nature"));
		word.setDoc_id(arg0.getInt("doc_id"));
		return word;
	}

}
