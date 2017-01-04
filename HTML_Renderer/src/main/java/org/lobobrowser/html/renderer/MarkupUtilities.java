/*
    GNU GENERAL LICENSE
    Copyright (C) 2006 The Lobo Project. Copyright (C) 2014 - 2017 Lobo Evolution

    This program is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    verion 3 of the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    General License for more details.

    You should have received a copy of the GNU General Public
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    

    Contact info: lobochief@users.sourceforge.net; ivan.difrancesco@yahoo.it
 */
/*
 * Created on Apr 16, 2005
 */
package org.lobobrowser.html.renderer;

import java.awt.Point;
import java.awt.Rectangle;

import org.lobobrowser.util.Diagnostics;

/**
 * The Class MarkupUtilities.
 *
 * @author J. H. S.
 */
public class MarkupUtilities {
    /**
     * Instantiates a new markup utilities.
     */
    private MarkupUtilities() {
        super();
    }

    /**
     * Find renderable.
     *
     * @param renderables
     *            the renderables
     * @param point
     *            the point
     * @param vertical
     *            the vertical
     * @return the boundable renderable
     */
    public static BoundableRenderable findRenderable(Renderable[] renderables,
            Point point, boolean vertical) {
        return findRenderable(renderables, point, 0, renderables.length,
                vertical);
    }

    /**
     * Find renderable.
     *
     * @param renderables
     *            the renderables
     * @param x
     *            the x
     * @param y
     *            the y
     * @param vertical
     *            the vertical
     * @return the boundable renderable
     */
    public static BoundableRenderable findRenderable(Renderable[] renderables,
            int x, int y, boolean vertical) {
        return findRenderable(renderables, x, y, 0, renderables.length,
                vertical);
    }

    /**
     * Find renderable.
     *
     * @param renderables
     *            the renderables
     * @param point
     *            the point
     * @param firstIndex
     *            the first index
     * @param length
     *            the length
     * @param vertical
     *            the vertical
     * @return the boundable renderable
     */
    private static BoundableRenderable findRenderable(Renderable[] renderables,
            Point point, int firstIndex, int length, boolean vertical) {
        return findRenderable(renderables, point.x, point.y, firstIndex,
                length, vertical);
    }

    /**
     * Find renderable.
     *
     * @param renderables
     *            the renderables
     * @param x
     *            the x
     * @param y
     *            the y
     * @param firstIndex
     *            the first index
     * @param length
     *            the length
     * @param vertical
     *            the vertical
     * @return the boundable renderable
     */
    private static BoundableRenderable findRenderable(Renderable[] renderables,
            int x, int y, int firstIndex, int length, boolean vertical) {
        if (length == 0) {
            return null;
        }
        if (length == 1) {
            Renderable r = renderables[firstIndex];
            if (!(r instanceof BoundableRenderable)) {
                return null;
            }
            BoundableRenderable br = (BoundableRenderable) r;
            Rectangle rbounds = br.getBounds();
            return rbounds.contains(x, y) ? br : null;
        } else {
            int middleIndex = firstIndex + (length / 2);
            Renderable r = renderables[middleIndex];
            Rectangle rbounds;
            if (r instanceof BoundableRenderable) {
                rbounds = ((BoundableRenderable) r).getBounds();
            } else {
                BoundableRenderable rleft = findRenderable(renderables, x, y,
                        firstIndex, middleIndex - firstIndex, vertical);
                if (rleft != null) {
                    return rleft;
                }
                return findRenderable(renderables, x, y, middleIndex + 1,
                        length - ((middleIndex - firstIndex) + 1), vertical);
            }
            if (rbounds.contains(x, y)) {
                return (BoundableRenderable) r;
            }
            if (vertical) {
                if (y < rbounds.y) {
                    return findRenderable(renderables, x, y, firstIndex,
                            middleIndex - firstIndex, vertical);
                } else {
                    return findRenderable(renderables, x, y, middleIndex + 1,
                            length - ((middleIndex - firstIndex) + 1), vertical);
                }
            } else {
                if (x < rbounds.x) {
                    return findRenderable(renderables, x, y, firstIndex,
                            middleIndex - firstIndex, vertical);
                } else {
                    return findRenderable(renderables, x, y, middleIndex + 1,
                            length - ((middleIndex - firstIndex) + 1), vertical);
                }
            }
        }
    }

    /**
     * Find renderables.
     *
     * @param renderables
     *            the renderables
     * @param clipArea
     *            the clip area
     * @param vertical
     *            the vertical
     * @return the range
     */
    public static Range findRenderables(Renderable[] renderables,
            Rectangle clipArea, boolean vertical) {
        return findRenderables(renderables, clipArea, 0, renderables.length,
                vertical);
    }

    /**
     * Find renderables.
     *
     * @param renderables
     *            the renderables
     * @param clipArea
     *            the clip area
     * @param firstIndex
     *            the first index
     * @param length
     *            the length
     * @param vertical
     *            the vertical
     * @return the range
     */
    private static Range findRenderables(Renderable[] renderables,
            Rectangle clipArea, int firstIndex, int length, boolean vertical) {
        if (length == 0) {
            return new Range(0, 0);
        }
        int offset1 = findFirstIndex(renderables, clipArea, firstIndex, length,
                vertical);
        int offset2 = findLastIndex(renderables, clipArea, firstIndex, length,
                vertical);
        if ((offset1 == -1) && (offset2 == -1)) {
            // if(logger.isEnabled(Level.INFO))logger.info("findRenderables(): Range not found for clipArea="
            // + clipArea + ",length=" + length);
            // for(int i = firstIndex; i < length; i++) {
            // logger.info("findRenderables(): renderable.bounds=" +
            // renderables[i].getBounds());
            //}
            return new Range(0, 0);
        }
        if (offset1 == -1) {
            offset1 = firstIndex;
        }
        if (offset2 == -1) {
            offset2 = (firstIndex + length) - 1;
        }
        return new Range(offset1, (offset2 - offset1) + 1);
    }

    /**
     * Find first index.
     *
     * @param renderables
     *            the renderables
     * @param clipArea
     *            the clip area
     * @param index
     *            the index
     * @param length
     *            the length
     * @param vertical
     *            the vertical
     * @return the int
     */
    private static int findFirstIndex(Renderable[] renderables,
            Rectangle clipArea, int index, int length, boolean vertical) {
        Diagnostics.Assert(length > 0, "length=" + length);
        if (length == 1) {
            Renderable r = renderables[index];
            Rectangle rbounds;
            if (r instanceof BoundableRenderable) {
                rbounds = ((BoundableRenderable) r).getBounds();
            } else {
                return -1;
            }
            if (intersects(rbounds, clipArea, vertical)) {
                return index;
            } else {
                return -1;
            }
        } else {
            int middleIndex = index + (length / 2);
            Renderable r = renderables[middleIndex];
            Rectangle rbounds;
            if (r instanceof BoundableRenderable) {
                rbounds = ((BoundableRenderable) r).getBounds();
            } else {
                int leftIndex = findFirstIndex(renderables, clipArea, index,
                        middleIndex - index, vertical);
                if (leftIndex != -1) {
                    return leftIndex;
                }
                return findFirstIndex(renderables, clipArea, middleIndex + 1,
                        length - ((middleIndex - index) + 1), vertical);
            }
            if (vertical) {
                if ((rbounds.y + rbounds.height) < clipArea.y) {
                    int newLen = length - ((middleIndex - index) + 1);
                    return newLen == 0 ? -1 : findFirstIndex(renderables,
                            clipArea, middleIndex + 1, newLen, vertical);
                } else {
                    int newLen = middleIndex - index;
                    int resultIdx = newLen == 0 ? -1 : findFirstIndex(
                            renderables, clipArea, index, newLen, vertical);
                    if (resultIdx == -1) {
                        if (intersects(clipArea, rbounds, vertical)) {
                            return middleIndex;
                        }
                    }
                    return resultIdx;
                }
            } else {
                if ((rbounds.x + rbounds.width) < clipArea.x) {
                    return findFirstIndex(renderables, clipArea,
                            middleIndex + 1, length - (middleIndex - index),
                            vertical);
                } else {
                    int resultIdx = findFirstIndex(renderables, clipArea,
                            index, middleIndex - index, vertical);
                    if (resultIdx == -1) {
                        if (intersects(clipArea, rbounds, vertical)) {
                            return middleIndex;
                        }
                    }
                    return resultIdx;
                }
            }
        }
    }

    /**
     * Find last index.
     *
     * @param renderables
     *            the renderables
     * @param clipArea
     *            the clip area
     * @param index
     *            the index
     * @param length
     *            the length
     * @param vertical
     *            the vertical
     * @return the int
     */
    private static int findLastIndex(Renderable[] renderables,
            Rectangle clipArea, int index, int length, boolean vertical) {
        Diagnostics.Assert(length > 0, "length<=0");
        if (length == 1) {
            Renderable r = renderables[index];
            Rectangle rbounds;
            if (r instanceof BoundableRenderable) {
                rbounds = ((BoundableRenderable) r).getBounds();
            } else {
                return -1;
            }
            if (intersects(clipArea, rbounds, vertical)) {
                return index;
            } else {
                return -1;
            }
        } else {
            int middleIndex = index + (length / 2);
            Renderable r = renderables[middleIndex];
            Rectangle rbounds;
            if (r instanceof BoundableRenderable) {
                rbounds = ((BoundableRenderable) r).getBounds();
            } else {
                int rightIndex = findLastIndex(renderables, clipArea,
                        middleIndex + 1, length - ((middleIndex - index) + 1),
                        vertical);
                if (rightIndex != -1) {
                    return rightIndex;
                }
                return findLastIndex(renderables, clipArea, index, middleIndex
                        - index, vertical);
            }
            if (vertical) {
                if (rbounds.y > (clipArea.y + clipArea.height)) {
                    return findLastIndex(renderables, clipArea, index,
                            middleIndex - index, vertical);
                } else {
                    int newLen = length - ((middleIndex - index) + 1);
                    int resultIdx = newLen == 0 ? -1 : findLastIndex(
                            renderables, clipArea, middleIndex + 1, newLen,
                            vertical);
                    if (resultIdx == -1) {
                        if (intersects(clipArea, rbounds, vertical)) {
                            return middleIndex;
                        }
                    }
                    return resultIdx;
                }
            } else {
                if (rbounds.x > (clipArea.x + clipArea.width)) {
                    return findLastIndex(renderables, clipArea, index,
                            middleIndex - index, vertical);
                } else {
                    int resultIdx = findLastIndex(renderables, clipArea,
                            middleIndex + 1, length
                            - ((middleIndex - index) + 1), vertical);
                    if (resultIdx == -1) {
                        if (intersects(clipArea, rbounds, vertical)) {
                            return middleIndex;
                        }
                    }
                    return resultIdx;
                }
            }
        }
    }

    /**
     * Intersects.
     *
     * @param rect1
     *            the rect1
     * @param rect2
     *            the rect2
     * @param vertical
     *            the vertical
     * @return true, if successful
     */
    private static boolean intersects(Rectangle rect1, Rectangle rect2,
            boolean vertical) {
        if (vertical) {
            return !((rect1.y > (rect2.y + rect2.height)) || (rect2.y > (rect1.y + rect1.height)));
        } else {
            return !((rect1.x > (rect2.x + rect2.width)) || (rect2.x > (rect1.x + rect1.width)));
        }
    }
}
