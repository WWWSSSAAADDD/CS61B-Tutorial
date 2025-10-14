public class Planet {
    /** Pos 位置 */
    public double xxPos;
    public double yyPos;

    /** Vel 速度 */
    public double xxVel;
    public double yyVel;

    /** mass 质量 */
    public double mass;

    /** 图像的文件名 */
    public String imgFileName;

    /** 构造函数*/
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** 复制构造函数*/
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** 返回另一个Planet和当前Planet的距离*/
    public double calcDistance(Planet other) {
        double dx = other.xxPos - xxPos;
        double dy = other.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /** 返回另一个Planet对当前Planet的作用力大小*/
    public double calcForceExertedBy(Planet other) {
        double distance = calcDistance(other);
        double G = 6.67e-11;
        return G * mass * other.mass / distance / distance;
    }

    /** 返回另一个Planet对当前Planet的作用力在x上的分量*/
    public double calcForceExertedByX(Planet other) {
        double distance = calcDistance(other);
        double F = calcForceExertedBy(other);
        double dx = other.xxPos - xxPos;
        return F * dx / distance;
    }

    /** 返回另一个Planet对当前Planet的作用力在y上的分量*/
    public double calcForceExertedByY(Planet other) {
        double distance = calcDistance(other);
        double F = calcForceExertedBy(other);
        double dy = other.yyPos - yyPos;
        return F * dy / distance;
    }

    /** 如果输入的Planet和当前Planet是同一个实例，返回true，否则返回false */
    private boolean equals(Planet other) {
        return xxPos == other.xxPos && yyPos == other.yyPos && mass == other.mass;
    }

    /** 返回所有Planet对当前Planet的合力在x上的分量 */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        int size = allPlanets.length;
        double netForceExertedByX = 0.0;
        for (int i = 0; i < size; i ++) {
            if (!equals(allPlanets[i])) {
                netForceExertedByX += calcForceExertedByX(allPlanets[i]);
            }
        }
        return netForceExertedByX;
    }

    /** 返回所有Planet对当前Planet的合力在y上的分量 */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        int size = allPlanets.length;
        double netForceExertedByY = 0.0;
        for (int i = 0; i < size; i ++) {
            if (!equals(allPlanets[i])) {
                netForceExertedByY += calcForceExertedByY(allPlanets[i]);
            }
        }
        return netForceExertedByY;
    }

    /** 返回输入力对当前Planet产生的加速度 */
    private double calcAcceleration(double F) {
        return F / mass;
    }

    public void update(double dt, double fX, double fY) {
        double aX = calcAcceleration(fX);
        double aY = calcAcceleration(fY);

        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
        return;
    }

    /** 绘制当前Planet */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" +imgFileName);
        return;
    }
}