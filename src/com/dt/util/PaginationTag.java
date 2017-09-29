package com.dt.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PaginationTag extends TagSupport{
	private static final long serialVersionUID = 1L;

	private String curPage;
	private String totalPage;
	private String pageSize;

	public int doStartTag() throws JspException {
	  JspWriter out = pageContext.getOut();
	  if (pageSize == null || pageSize == "") {
		  pageSize = "1";
	  }
	  int pageNumber=0;
	  if (Integer.parseInt(totalPage)%Integer.parseInt(pageSize) == 0 ){ 
		  pageNumber = Integer.parseInt(totalPage)/Integer.parseInt(pageSize);
	  } else{
		  pageNumber = Integer.parseInt(totalPage)/Integer.parseInt(pageSize) + 1; 
	  }
	  if (Integer.parseInt(curPage) < 0) {
		  curPage = "1";
	  }
	  try {
		  out.print("<br/><span id='fenye' style='padding-top:10px'>");
		  if(pageNumber>1){
		  out.print("<span onclick=page(0) style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:10px;background-color:#F5F5F4\">&nbsp;首页&nbsp;</span>&nbsp;");
		  if((Integer.parseInt(curPage)-1<0)){
			  out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:10px;background-color:#F5F5F4\"><< 上一页</span>&nbsp;");
		  }else{
			  out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:10px;background-color:#F5F5F4\" " +
				  		"onclick=page("+(Integer.parseInt(curPage) - 1)+")><< 上一页</span>&nbsp;&nbsp;");
		  }
		 
		  
		  int start=(Integer.parseInt(curPage))-4;
		  if(start<=0){
			  start = 0;
		  }
		  int end=7;
		  if(Integer.parseInt(curPage)-start==4){
			  start=start+1;
			  end=end+start;
			  if(end>=pageNumber){
				  end=pageNumber;
			  }
		  }
		  if(end>=pageNumber){
			  end=pageNumber;
		  }
		  for (int i = start+1; i <=end; i++) {
			    if(i-1== Integer.parseInt(curPage)){
			    	out.print("<span style=\"cursor: hand;cursor:pointer; color:#ED1F32;background-color:#F4921F;border: 1px solid #FFE7A4;padding:10px; \" onclick=page("+(i-1)+")>&nbsp;<b>"+i+"</b>&nbsp;</span>&nbsp;&nbsp;");
			    }else{
			    	out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:10px;background-color:#F5F5F4\" onclick=page("+(i-1)+")>&nbsp;"+i+"&nbsp;</span>&nbsp;&nbsp;");
			    }
		  }
		  if(Integer.parseInt(curPage)>=pageNumber-1){
			  out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:10px;background-color:#F5F5F4\">最后一页 >></span>&nbsp;");
		  }else{
			  out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:10px;background-color:#F5F5F4\" " +
				  		"onclick=page("+(Integer.parseInt(curPage)+1)+")>下一页 >></span><span class=f7_1>" +
				  				"</span><span></span><span></span>&nbsp;");
		  }
		  if(Integer.parseInt(curPage)>=pageNumber-1){
			  out.print("<span></span>");
		  }else{
			  out.print("<span onclick=page("+(pageNumber-1)+") style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:10px;background-color:#F5F5F4\">&nbsp;末页&nbsp;</span>");
		  }
		  out.print("</span>");
		  out.println("<input type=hidden id='curPage' name=curPage>");
		  out.println("<script type=\"text/javascript\">");
		  out.println("function page(n){");
		 // out.println("document.getElementById(\"myform\").action='';");
		  out.println("document.getElementById(\"curPage\").value=n;");
		  out.println("document.getElementById(\"myform\").submit();");
		  out.println("}");
		  out.println("</script>");
		  }
		  
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	  return super.doStartTag();
	}

	public void setCurPage(String curPage) {
	  this.curPage = curPage;
	}

	public void setPageSize(String pageSize) {
	  this.pageSize = pageSize;
	}
	public void setTotalPage(String totalPage) {
	  this.totalPage = totalPage;
	}

}
