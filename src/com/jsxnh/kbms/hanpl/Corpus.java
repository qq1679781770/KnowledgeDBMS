package com.jsxnh.kbms.hanpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.jsxnh.kbms.hanpl.BaseDao;
import com.jsxnh.kbms.hanpl.Document;
import com.jsxnh.kbms.hanpl.Word;
import com.jsxnh.kbms.hanpl.Myterm;

public class Corpus {

	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	BaseDao dao=ctx.getBean(BaseDao.class);
	
	
	public static void main(String[] args) {
//		for(int i=2;i<10;i++){
//			String filename="file\\00"+i+".txt";
//			new Corpus().saveCorpus(filename);
//		}
		new Corpus().saveCorpus("file\\013.txt");
	}
	
	public void saveCorpus(String filename){
		BufferedReader in = null;
		Integer doc_id=Calendar.getInstance().get(Calendar.DAY_OF_YEAR)*100+Calendar.getInstance().get(Calendar.MINUTE)*100+
				       Calendar.getInstance().get(Calendar.SECOND);
		try {
			in = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuilder str=new StringBuilder();
		String s;
		try {
			while((s=in.readLine())!=null){
				str.append(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc=new Document();
		doc.setContext(str.toString());
		doc.setId(doc_id);
		dao.saveDoc(doc);
		List<Term> termlist=NLPTokenizer.segment(str.toString());
		HashSet<Myterm> termset=new HashSet<Myterm>();
		for(Term term:termlist){
			Myterm myterm=new Myterm(term.word, term.nature);
			termset.add(myterm);
		}
		
		Iterator<Myterm> it=termset.iterator();
		while(it.hasNext()){
			Myterm myterm=it.next();
			Word word=new Word();
			word.setWord(myterm.getWord());
			word.setNature(myterm.getNature().toString());
			word.setDoc_id(doc_id);
			dao.saveWord(word);
		}		
	}
}
