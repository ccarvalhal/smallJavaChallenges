package pt.ccarvalhal;

/**
 * Created by ccarvalhal on 08/09/2018.
 *
 * This Class provides a method to rotate, in anticlockwise direction, the rings of a square matrix of integers by
 * a specified quantity of positions.
 *
 * For example, the matrix below on the left, after the rotation, in anticlockwise direction, of its rings by 2 positions
 * will be transformed into the matrix below on the right:
 *
 *             1  2  3  4  5           3  4  5  6  7
 *            16 17 18 19  6           2 15 20 21  8
 *            15 24 25 20  7           1 18 25 22  9
 *            14 23 22 21  8          16 17 24 23 10
 *            13 12 11 10  9          15 14 13 12 11
 */
public class MatrixRingsRotation {
    // The directions for traversing a ring of the matrix.
    private enum Direction {LEFT_TO_RIGHT, TOP_TO_BOTTOM, RIGHT_TO_LEFT, BOTTOM_TO_TOP}

    /**
     * Fetches all the rings of the square matrix referred by the parameter "matrix".
     *
     * @param matrix The square matrix whose rings we want to get.
     * @return The rings of the square matrix referred by the parameter "matrix" traversed in clockwise direction and
     *      starting in the top left corner.
     */
    public static int[][] fetchRings(int[][] matrix) {
        // The quantity of rings in the matrix is: Math.round(matrix.length / 2)
        int[][] rings = new int[Math.round((float)matrix.length / 2)][];

        int originalSize = matrix.length;
        int startX = 0, startY = 0, size = originalSize, ringIndex = 0;
        while (startX < Math.round((float)originalSize / 2)) {
            rings[ringIndex++] = fetchRing(matrix, startX, startY, size);
            startX++;
            startY++;
            size -= 2;
        }
        return rings;
    }

    /**
     * Fetches the ring that delimit the square sub-matrix of the square matrix referred by the parameter "matrix"
     * with the size referred by the parameter "size" and starting at the position referred by the parameters "startX"
     * and "startY".
     *
     * The itens of the ring are ordered in clockwise direction.
     *
     * @param matrix The square matrix whose ring wewant to get.
     * @param startX The row of the matrix the ring start in.
     * @param startY The column of the matrix the ring start in.
     * @param size The size of the square sub-matrix the ring delimit.
     * @return The itens, orderer in clockwise direction, of the ring that delimit the square sub-matrix of the square
     *      matrix referred by the parameter "matrix" with the size referred by the parameter "size" and starting at the
     *      position referred by the parameters "startX" and "startY".
     */
    public static int[] fetchRing(int[][] matrix, int startX, int startY, int size) {
        // The quantity of itens in the ring is: size * 4 - 4
        int[] ring = new int[size * 4 - 4];
        int x = startX, y = startY;

        Direction direction = Direction.LEFT_TO_RIGHT;

        if (size == 1) {
            return new int[] {matrix[startX][startY]};
        }

        int index = 0;
        do {
            ring[index++] = matrix[x][y];
            switch (direction) {
                case LEFT_TO_RIGHT:
                    if (y < startY + size - 1) {
                        y++;
                        break;
                    }
                    direction = Direction.TOP_TO_BOTTOM;
                    x++;
                    break;
                case TOP_TO_BOTTOM:
                    if (x < startX + size - 1) {
                        x++;
                        break;
                    }
                    direction = Direction.RIGHT_TO_LEFT;
                    y--;
                    break;
                case RIGHT_TO_LEFT:
                    if (y > startY) {
                        y--;
                        break;
                    }
                    direction = Direction.BOTTOM_TO_TOP;
                    x--;
                    break;
                case BOTTOM_TO_TOP:
                    if (x > startX) {
                        x--;
                        break;
                    }
                    break;
            }
        } while (x != startX || y != startY);
        return ring;
    }

    /**
     * Rotate in anticlockwise direction the list referred by the parameter "list" by the quantity of positions
     * referred by the parameter "positions".
     *
     * @param list The list to be rotated in anticlockwise direction by the quantity of positions referred by the
     *      parameter "positions".
     * @param positions The quantity of positions to rotate in anticlockwise direction the list referred by the
     *      parameter "list".
     * @return The list referred by the parameter "list" rotated in anticlockwise direction by the quantity of
     *      positions referred by the parameter "positions".
     */
    public static int[] rotateAnticlockwise(int[] list, int positions) {
        if (list.length <= 1) {
            return list;
        }

        // Removes full rotations from "positions".
        positions = positions % list.length;

        if (positions == 0) {
            return list;
        }

        int[] rotated = new int[list.length];
        for (int origIndex = 0; origIndex < list.length; origIndex++) {
            int rotIndex = origIndex - positions;
            if (rotIndex < 0) {
                rotIndex = list.length + rotIndex;
            }
            rotated[rotIndex] = list[origIndex];

        }
        return rotated;
    }

    /**
     * Reassemble the rings referred by the parameter "rings" into a matrix.
     *
     * @param rings The orderer list of rings to be reassembled into a matrix.
     * @return The matrix assembled using the rings referred by the parameter "rings".
     */
    public static int[][] reassembleRingsOnMatrix(int[][] rings) {
        // Calculates the size of the square matrix.
        int matrixSize = (rings[0].length + 4) / 4;
        int[][] matrix = new int[matrixSize][matrixSize];

        for (int ringIndex = 0; ringIndex < rings.length; ringIndex++) {
            int[] ring = rings[ringIndex];
            int size = (ring.length + 4) / 4;
            int x = ringIndex, y = ringIndex;
            Direction direction = Direction.LEFT_TO_RIGHT;
            for (int aRing : ring) {
                matrix[x][y] = aRing;
                switch (direction) {
                    case LEFT_TO_RIGHT:
                        if (y < ringIndex + size - 1) {
                            y++;
                            break;
                        }
                        direction = Direction.TOP_TO_BOTTOM;
                        x++;
                        break;
                    case TOP_TO_BOTTOM:
                        if (x < ringIndex + size - 1) {
                            x++;
                            break;
                        }
                        direction = Direction.RIGHT_TO_LEFT;
                        y--;
                        break;
                    case RIGHT_TO_LEFT:
                        if (y > ringIndex) {
                            y--;
                            break;
                        }
                        direction = Direction.BOTTOM_TO_TOP;
                        x--;
                        break;
                    case BOTTOM_TO_TOP:
                        if (x > ringIndex) {
                            x--;
                            break;
                        }
                        break;
                }
            }
        }

        return matrix;
    }

    /**
     * Rotates the rings of the matrix referred by the parameter "matrix" the quantity of positions referred by the
     * parameter "positions".
     *
     * @param matrix The matrix whose rings will be rotated.
     * @param positions The quantity of positions the rings of the matrix will be rotated
     * @return The matrix referred by the parameter "matrix" with the rings rotated by the quantity of positions
     *      referred by the parameter "positions".
     */
    public static int[][] rotateMatrix(int[][] matrix, int positions) {
        int[][] rings = fetchRings(matrix);
        for (int i = 0, ringsLength = rings.length; i < ringsLength; i++) {
            rings[i] = rotateAnticlockwise(rings[i], positions);
        }
        return reassembleRingsOnMatrix(rings);
    }
}
