package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PaperDao;
/**
 * Servlet implementation class BrowsePaperServletII
 */
public class BrowsePaperServletII extends HttpServlet {       //查看试卷功能中浏览试卷界面
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowsePaperServletII() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		PaperDao paperDao=new PaperDao();           //实例化SQL层
		int j=0;
		ServletContext ctx=this.getServletContext();          //获取web。xml中的常量
		String server=ctx.getInitParameter("server");
		String db=ctx.getInitParameter("db");
		String user=ctx.getInitParameter("user");
		String pwd=ctx.getInitParameter("pwd");
		String pname=new String(request.getParameter("pname"));       //获取试卷名字
		String[] pChoiceQu=new String[100];
		String[] pChoiceOp1=new String[100];
		String[] pChoiceOp2=new String[100];
		String[] pChoiceOp3=new String[100];
		String[] pChoiceOp4=new String[100];
		String[] pChoiceAn=new String[100];
		String[] pFillQu=new String[100];
		String[] pFillAn=new String[100];
		HttpSession session=request.getSession();
		String name=(String)session.getAttribute("Name");
		try {
			paperDao.getConn(server, db, user, pwd);         //获取连接
			for(int i=0;paperDao.getChoiceQuestions(pname)[i]!=null;i++) {
				pChoiceQu[i]=paperDao.getChoiceQuestions(pname)[i];          //获取选择题题目数组
				pChoiceOp1[i]=paperDao.getChoiceOp1(pname)[i];               //获取选项A数组
				pChoiceOp2[i]=paperDao.getChoiceOp2(pname)[i];               //获取选项B数组
				pChoiceOp3[i]=paperDao.getChoiceOp3(pname)[i];               //获取选项C数组
				pChoiceOp4[i]=paperDao.getChoiceOp4(pname)[i];               //获取选项D数组
				pChoiceAn[i]=paperDao.getChoiceAnswers(pname)[i];            //获取选择题答案数组
			}
			for(int i=0;paperDao.getFillQuestions(pname)[i]!=null;i++) {
				pFillQu[i]=paperDao.getFillQuestions(pname)[i];          //获取填空题题目数组
				pFillAn[i]=paperDao.getFillAnswers(pname)[i];            //获取填空题答案数组
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		} 
		catch (InstantiationException e) {
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		out.print("<html>");
		out.print("<head><title>查看试卷</title>");
		out.print("<style type='text/css'>");
		out.print(".top_nei { width:100%;text-align:center;height:40px;background-color:#1E90FF;line-height:40px;font-size: 18px;font-family: '微软雅黑';color:white;}");
		out.print(".menu {line-height:50px;}");
		out.print("p a:hover {color:#FF6600;}");
		out.print(".STYLE4 {font-size: 18px;font-weight: bold;font-family: '微软雅黑';}");
		out.print("a:link,a:visited {color:#000;text-decoration:none;}");
		out.print("#menu {width: 145px;margin: auto;border: 1px solid #999;left: 64px;position: absolute;font-size: 14px;top: 209px;}");
		out.print("#div1 {display: none;font-size: 12px;position: relative;left: 192px;top: 5px;background-color: White;width: 100px;height: 30px;}");
		out.print("#div2 {display: none;font-size: 16px;position: relative;left: 192px;top: 25px;background-color: White;padding: 5px 10px 0px 10px;width: 100px;}");
		out.print("#div3 {display: none;font-size: 12px;position: relative;left: 192px;top: 60px;background-color: White;padding: 5px 10px 0px 10px;width: 100px;}");
		out.print("#div4 {display: none;font-size: 12px;position: relative;left: 192px;top: 90px;background-color: White;padding: 5px 10px 0px 10px;width: 100px;}");
		out.print(".STYLE5 {color: #000000;font-weight: bold;}");
		out.print(".STYLE7 {font-size: 18px;}");
		out.print("</style>");
		out.print("<script language='javascript' type='text/javascript'>");
		out.print("function showDiv(divName) {document.getElementById(divName).style.display = 'block';}");
		out.print("function hiddenDiv(divName) {document.getElementById(divName).style.display = 'none';}");
		out.print("</script>");
		out.print("<title>查看试卷</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<p align='right'><a href='personal-4.jsp' class='STYLE4'>首页</a></p>");
		out.print("<div class='top_nei'>");
		out.print("<div align='left'>");
		out.print("当前用户：");
		out.print(name);
		out.print("</div>");
		out.print("</div>");
		out.print("<p>&nbsp;</p>");
		out.print("</head>");
		out.print("<body>");
		out.print(pname+"<br>");
		out.print("一、选择题<br>");
		for(int i=0;paperDao.getChoiceQuestions(pname)[i]!=null;i++) {          //遍历输出
			out.print((i+1)+"、"+pChoiceQu[i]+"<br>");
			out.print("A、"+pChoiceOp1[i]+"<br>");
			out.print("B、"+pChoiceOp2[i]+"<br>");
			out.print("C、"+pChoiceOp3[i]+"<br>");
			out.print("D、"+pChoiceOp4[i]+"<br>");
			out.print("答案："+pChoiceAn[i]+"<br><br>");
			j++;
		}
		out.print("二、填空题<br>");
		for(int i=0;paperDao.getFillQuestions(pname)[i]!=null;i++) {          //遍历输出
			j++;
			out.print(j+"、"+pFillQu[i]+"<br>");
			out.print("答案："+pFillAn[i]+"<br><br>");
		}
		paperDao.closeAll();                 //关闭全部数据集和连接
		out.print("<form action='EditPaperServletII' method='post'>");
		out.print("<input type='hidden' name='pname' value='"+pname+"'>");
		out.print("<input type='submit' value='编辑该试卷'>");
		out.print("<input type='button' value='返回' onclick=\"window.location.href='/OnlineExam/BrowsePaperServlet'\" />");
		out.print("<input type='button' value='命题菜单' onclick=\"location.href='tkmindex.jsp'\" /><br>");
		out.print("</form>");
		out.print("</body>");
		out.print("</html>");
	}

}
