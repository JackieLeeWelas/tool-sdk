package com.jackie.lee.common;

/**
 * Created by lxb on 2019/6/23.
 */
public class DistanceUtil {
    /**
     * 计算两个坐标之间的距离
     * @param lat1 lat1
     * @param lng1 lng1
     * @param lat2 lat2
     * @param lng2 lng2
     * @return double
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        int MAXITERS = 20;
        lat1 *= Math.PI / 180.0;
        lat2 *= Math.PI / 180.0;
        lng1 *= Math.PI / 180.0;
        lng2 *= Math.PI / 180.0;

        double a = 6378137.0; // WGS84 major axis
        double b = 6356752.3142; // WGS84 semi-major axis
        double f = (a - b) / a;
        double aSqMinusBSqOverBSq = (a * a - b * b) / (b * b);

        double L = lng2 - lng1;
        double A = 0.0;
        double U1 = Math.atan((1.0 - f) * Math.tan(lat1));
        double U2 = Math.atan((1.0 - f) * Math.tan(lat2));

        double cosU1 = Math.cos(U1);
        double cosU2 = Math.cos(U2);
        double sinU1 = Math.sin(U1);
        double sinU2 = Math.sin(U2);
        double cosU1cosU2 = cosU1 * cosU2;
        double sinU1sinU2 = sinU1 * sinU2;

        double sigma = 0.0, deltaSigma = 0.0, cosSqAlpha, cos2SM, cosSigma, sinSigma, cosLambda, sinLambda;

        double lambda = L; // initial guess
        for (int iter = 0; iter < MAXITERS; iter++) {
            double lambdaOrig = lambda;
            cosLambda = Math.cos(lambda);
            sinLambda = Math.sin(lambda);
            double t1 = cosU2 * sinLambda;
            double t2 = cosU1 * sinU2 - sinU1 * cosU2 * cosLambda;
            double sinSqSigma = t1 * t1 + t2 * t2; // (14)
            sinSigma = Math.sqrt(sinSqSigma);
            cosSigma = sinU1sinU2 + cosU1cosU2 * cosLambda; // (15)
            sigma = Math.atan2(sinSigma, cosSigma); // (16)
            double sinAlpha = ((new Double(sinSigma)).equals(new Double(0.0))) ? 0.0 : cosU1cosU2 * sinLambda
                    / sinSigma; // (17)
            cosSqAlpha = 1.0 - sinAlpha * sinAlpha;
            cos2SM = ((new Double(cosSqAlpha)).equals(new Double(0.0))) ? 0.0 : cosSigma - 2.0 * sinU1sinU2
                    / cosSqAlpha; // (18)

            double uSquared = cosSqAlpha * aSqMinusBSqOverBSq; // defn
            A = 1
                    + (uSquared / 16384.0)
                    * // (3)
                    (4096.0 + uSquared
                            * (-768 + uSquared * (320.0 - 175.0 * uSquared)));
            double B = (uSquared / 1024.0) * // (4)
                    (256.0 + uSquared
                            * (-128.0 + uSquared * (74.0 - 47.0 * uSquared)));
            double C = (f / 16.0) * cosSqAlpha
                    * (4.0 + f * (4.0 - 3.0 * cosSqAlpha)); // (10)
            double cos2SMSq = cos2SM * cos2SM;
            deltaSigma = B
                    * sinSigma
                    * // (6)
                    (cos2SM + (B / 4.0)
                            * (cosSigma * (-1.0 + 2.0 * cos2SMSq) - (B / 6.0)
                            * cos2SM
                            * (-3.0 + 4.0 * sinSigma * sinSigma)
                            * (-3.0 + 4.0 * cos2SMSq)));

            lambda = L
                    + (1.0 - C)
                    * f
                    * sinAlpha
                    * (sigma + C
                    * sinSigma
                    * (cos2SM + C * cosSigma
                    * (-1.0 + 2.0 * cos2SM * cos2SM))); // (11)

            double delta = (lambda - lambdaOrig) / lambda;
            if (Math.abs(delta) < 1.0e-12) {
                break;
            }
        }
        return (float) (b * A * (sigma - deltaSigma));
    }

    /**
     * 格式化展示的距离
     * @param distance
     * @return
     */
    public static String getDistancStr(double distance) {
        String distanceVo = null;
        int dis = (int) distance / 10 * 10;// 精确到十位
        if (dis < 100) {
            distanceVo = "<100m";
        }
        if (dis >= 100 && dis < 1000) {
            distanceVo = dis + "m";
        }
        if (dis >= 1000 && dis < 100000) {
            distanceVo = (float) (Math.round((float) dis / 1000 * 10)) / 10 + "km";
        }
        return distanceVo;
    }
}
