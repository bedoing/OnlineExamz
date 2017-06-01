package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.PaperDao;
import entity.Paper;

/**
 * Servlet implementation class BigEditPaperServletIII
 */
public class BigEditPaperServletIII extends HttpServlet {               //大幅度修改试卷功能中提交更改界面
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BigEditPaperServletIII() {
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
		int j=0;             //计数器
		String pNo=null;
		String pName=new String(request.getParameter("pname"));             //获取试卷名称
		String pChoShu=new String(request.getParameter("pcshu"));           //获取选择题个数
		String pChoPoi=new String(request.getParameter("pcpoint"));         //获取选择题分值
		String pFillShu=new String(request.getParameter("pfshu"));          //获取填空题个数
		String pFillPoi=new String(request.getParameter("pfpoint"));        //获取填空题分值
		int _pChoShu=Integer.parseInt(pChoShu);
		int _pFillShu=Integer.parseInt(pFillShu);
		Paper paper=new Paper();           //实例化数据层
		paper.setpName(pName);             //数据层赋值
		paper.setpChoShu(pChoShu);
		paper.setpChoPoi(pChoPoi);
		paper.setpFillShu(pFillShu);
		paper.setpFillPoi(pFillPoi);
		ServletContext ctx=this.getServletContext();          //获取web。xml中的常量
		String server=ctx.getInitParameter("server");
		String db=ctx.getInitParameter("db");
		String user=ctx.getInitParameter("user");
		String pwd=ctx.getInitParameter("pwd");
		PaperDao paperDao=new PaperDao();           //实例化SQL层
		try {
			paperDao.getConn(server, db, user, pwd);         //获取连接
			pNo=paperDao.getPaperNo(pName);                  //获取试卷编号
			paperDao.deletePaper(pNo);                       //试卷初始化
			paperDao.addQuShu(paper, pNo);                   //向数据库写入试卷属性
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			paperDao.closeAll();                 //关闭全部数据集和连接
		}
		paper.setpNo(pNo);
		out.print("<form action='BrowsePaperServletII' method='post'>");
		for(int i=0;i<_pChoShu;i++) {
			String pChoQu=new String(request.getParameter("pcq"+i));           //获取选择题题目
			String op1=new String(request.getParameter("op1"+i));              //获取A选项
			String op2=new String(request.getParameter("op2"+i));              //获取B选项
			String op3=new String(request.getParameter("op3"+i));              //获取C选项
			String op4=new String(request.getParameter("op4"+i));              //获取D选项
			String pChoAn=new String(request.getParameter("pca"+i));           //获取选择题答案
			paper.setpMun((i+1)+"");
			paper.setpChoQu(pChoQu);           //设置各数值
			paper.setOp1(op1);
			paper.setOp2(op2);
			paper.setOp3(op3);
			paper.setOp4(op4);
			paper.setpChoAn(pChoAn);
			j++;
			try {
				paperDao.getConn(server, db, user, pwd);
				if(paperDao.addChoiceQu(paper,pNo)==1) {                //向数据库写入选择题
					out.print("更新选择题第"+(i+1)+"题成功！<br/>");
				}
				else {
					out.print("更新选择题第"+(i+1)+"题失败！<br/>");
				}
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				paperDao.closeAll();                 //关闭全部数据集和连接
			}
		}
		for(int i=0;i<_pFillShu;i++) {
			j++;
			paper.setpMun(j+"");
			String pFillQu=new String(request.getParameter("pfq"+i));           //获取填空题题目
			String pFillAn=new String(request.getParameter("pfa"+i));           //获取填空题答案
			paper.setpFillQu(pFillQu);           //设置各数值
			paper.setpFillAn(pFillAn);
			try {
				paperDao.getConn(server, db, user, pwd);
				if(paperDao.addFillQu(paper,pNo)==1) {                //向数据库写入填空题
					out.print("更新填空题第"+(i+1)+"题成功！<br/>");
				}
				else {
					out.print("更新填空题第"+(i+1)+"题失败！<br/>");
				}
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		out.print("<input type='hidden' name='pname' value='"+pName+"' />");
		out.print("<input type='submit' value='浏览新建试卷'  />");
		out.print("<input type='button' value='命题菜单' onclick=\"location.href='tkmindex.jsp'\" /><br>");
		out.print("</form>");
	}

}
