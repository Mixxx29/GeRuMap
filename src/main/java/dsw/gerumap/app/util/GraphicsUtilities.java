package dsw.gerumap.app.util;

import java.awt.*;
import java.awt.geom.Point2D;

public class GraphicsUtilities {

    public static Point worldToScreenPoint(Point2D.Float worldPoint, Point2D.Float offset, float scale) {
        Point screenPoint = new Point();
        screenPoint.x = (int)((worldPoint.x - offset.x) * scale);
        screenPoint.y = (int)((worldPoint.y - offset.y) * scale);
        return screenPoint;
    }

    public static Point2D.Float screenToWorldPoint(Point screenPoint, Point2D.Float offset, float scale) {
        Point2D.Float worldPoint = new Point2D.Float();
        worldPoint.x = (float)(screenPoint.x / scale + offset.x);
        worldPoint.y = (float)(screenPoint.y / scale + offset.y);
        return worldPoint;
    }
}