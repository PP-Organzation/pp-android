<%@ page language="java" pageEncoding="UTF-8" contentType="image/jpeg"
	import="java.awt.*, java.awt.image.*,java.util.*,javax.imageio.*"%>
<%!Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	out.clear();//这句针对resin服务器，如果是tomacat可以不要这句 
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	//设置图片的宽度和高度
	int width = 60, height = 20;
	//创建缓存图片
	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	//在缓存图片上创建画板对象
	Graphics g = image.getGraphics();
	//随机函数
	Random random = new Random();
	//调用上面的函数画板背景色，灰白
	g.setColor(getRandColor(200, 250));
	//画一个矩形
	g.fillRect(0, 0, width, height);
	//字体名称，样式，大小
	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	//调用上面的函数产生验证码上面的干扰线155条淡灰色,现在有自动辨别图片文字的办法，所以需要干扰线
	g.setColor(getRandColor(160, 200));
	for (int i = 0; i < 155; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(12);
		int yl = random.nextInt(12);
		g.drawLine(x, y, x + xl, y + yl);
	}
	 //保存随机数字的字符串
	String sRand = "";
	 //生成4个随机数，并绘制到画板上面
	for (int i = 0; i < 4; i++) {
		String rand = String.valueOf(random.nextInt(10));
		sRand += rand;
		//设置数字绘制时的颜色
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
				.nextInt(110), 20 + random.nextInt(110)));
		//绘制数字
		g.drawString(rand, 13 * i + 6, 16);
	}
	// 将认证码存入SESSION 
	//将验证码存入session中
	session.setAttribute("rand", sRand);
	g.dispose();
	//图片IO流输入到客户端
	ImageIO.write(image, "JPEG", response.getOutputStream());
%>