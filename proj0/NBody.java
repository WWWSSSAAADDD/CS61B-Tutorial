public class NBody {
    /** 从文件中读取宇宙的Radius */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readDouble();        //跳过第一个“行星数量”
        double radius = in.readDouble();
        return radius;
    }

    /** 从文件中读取，并返回一个包含所有行星的Planet数组 */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int planetNums = in.readInt();
        double radius = in.readDouble();

        Planet[] ans = new Planet[planetNums];

        for (int i = 0; i < planetNums; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            ans[i] = new Planet(xPos, yPos, xV, yV, m, img);
        }

        return ans;
    }

    public static void main(String[] args) {
        double T = new Double(args[0]);
        double dt = new Double(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        int nums = allPlanets.length;

        //初始设置
        StdDraw.setScale(-radius, radius);   //需要根radius有关系
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();

        for (int time = 0; time < T; time += dt) {
            double[] xForces = new double[nums];
            double[] yForces = new double[nums];
            for (int i = 0; i < nums; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            for (int i = 0; i <  nums; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            //绘制所有Planet
            for (int i = 0; i < nums; i++) {
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        // 输出最终状态
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }

        return;
    }

}

//		In in = new In("BasicInDemo_input_file.txt");