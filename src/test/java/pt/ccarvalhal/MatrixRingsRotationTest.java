package pt.ccarvalhal;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by ccarvalhal on 08/09/2018.
 */
public class MatrixRingsRotationTest {
    @Test
    public void fetchRings() throws Exception {
        // Contains matrizes.
        int[][][] testCasesInput = {
                {
                        {1, 2, 3, 4, 5},
                        {16, 17, 18, 19, 6},
                        {15, 24, 25, 20, 7},
                        {14, 23, 22, 21, 8},
                        {13, 12, 11, 10, 9}
                },
                {
                        {1, 2, 3, 4},
                        {12, 13, 14, 5},
                        {11, 16, 15, 6},
                        {10, 9, 8, 7}
                },
                {
                        {1, 2, 3},
                        {8, 9, 4},
                        {7, 6, 5}
                },
                {
                        {1, 2},
                        {4, 3}
                },
                {
                        {1}
                }
        };

        // Contains the rings of the matrizes.
        int[][][] testCasesOutput = {
                {
                        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
                        {17, 18, 19, 20, 21, 22, 23, 24},
                        {25}
                },
                {
                        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
                        {13, 14, 15, 16}
                },
                {
                        {1, 2, 3, 4, 5, 6, 7, 8},
                        {9}
                },
                {
                        {1, 2, 3, 4}
                },
                {
                        {1}
                }
        };

        int[][] outValue;
        for (int inIndex = 0, outIndex = 0; inIndex < testCasesInput.length; inIndex++, outIndex++) {
            outValue = MatrixRingsRotation.fetchRings(testCasesInput[inIndex]);
            System.out.println(String.format("@Test: fetchRings():\nmatrix: %s\nrings: %s",
                    Arrays.deepToString(testCasesInput[inIndex]), Arrays.deepToString(outValue)));
            assertArrayEquals(testCasesOutput[outIndex], outValue);
        }
    }

    @Test
    public void fetchRing() throws Exception {
        // Contains the matrix.
        int[][] testCaseInputMatrix = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9}
        };
        // Contains the value for the parmeters {startX, startY, size}.
        int[][] testCaseInputOtherParameters = {
                {0, 0, 5},
                {1, 1, 3},
                {2, 2, 1}
        };

        // Contains the rings of the matrix for each one of the parameterization.
        int[][] testCasesOutput = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
                {17, 18, 19, 20, 21, 22, 23, 24},
                {25}
        };

        int[] outValue;
        for (int inIndex = 0, outIndex = 0; inIndex < testCaseInputOtherParameters.length; inIndex++, outIndex++) {
            int[] testCaseInputOtherParameter = testCaseInputOtherParameters[inIndex];
            int startX = testCaseInputOtherParameter[0];
            int startY = testCaseInputOtherParameter[1];
            int size = testCaseInputOtherParameter[2];
            outValue = MatrixRingsRotation.fetchRing(testCaseInputMatrix, startX, startY, size);
            System.out.println(String.format("@Test: fetchRing():\nmatrix: %s\nstartX: %d; startY: %d; size: %d\nring: %s",
                    Arrays.deepToString(testCaseInputMatrix), startX, startY, size, Arrays.toString(outValue)));
            assertArrayEquals(testCasesOutput[outIndex], outValue);
        }
    }

    @Test
    public void rotateAnticlockwise() throws Exception {
        // Contains the list to be rotated.
        int[][] testCasesInputLists = {
                {17, 18, 19, 20, 21, 22, 23, 24},
                {25}
        };
        // Contains the quantity of positions to rotate the correspondent list in "testCasesInputLists".
        int[][] testCasesInputPositions = {
                {0, 1, 2, 4, 8, 16, 19},
                {0, 1, 2}
        };

        // Contains the lists in "testCasesInputLists" rotated the quantity of positions in the correspondent entry of
        // "testCasesInputPositions".
        int[][][] testCasesOutput = {
                {
                        {17, 18, 19, 20, 21, 22, 23, 24},
                        {18, 19, 20, 21, 22, 23, 24, 17},
                        {19, 20, 21, 22, 23, 24, 17, 18},
                        {21, 22, 23, 24, 17, 18, 19, 20},
                        {17, 18, 19, 20, 21, 22, 23, 24},
                        {17, 18, 19, 20, 21, 22, 23, 24},
                        {20, 21, 22, 23, 24, 17, 18, 19}
                },
                {
                        {25},
                        {25},
                        {25}
                }
        };

        int[] outValue;
        for (int inIndex = 0; inIndex < testCasesInputLists.length; inIndex++) {
            int[] testCasesInputPosition = testCasesInputPositions[inIndex];
            for (int posIndex = 0; posIndex < testCasesInputPosition.length; posIndex++) {
                int[] testCaseInputList = testCasesInputLists[inIndex];
                int testCaseInputPosition = testCasesInputPosition[posIndex];
                outValue = MatrixRingsRotation.rotateAnticlockwise(testCaseInputList, testCaseInputPosition);
                System.out.println(String.format("@Test: rotateAnticlockwise():\nmatrix: %s\npositions: %d\nrotated: %s",
                        Arrays.toString(testCaseInputList), testCaseInputPosition, Arrays.toString(outValue)));
                assertArrayEquals(testCasesOutput[inIndex][posIndex], outValue);
            }
        }
    }

    @Test
    public void reassembleRingsOnMatrix() throws Exception {
        // Contains the rings of the matrizes.
        int[][][] testCasesInput = {
                {
                        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
                        {17, 18, 19, 20, 21, 22, 23, 24},
                        {25}
                },
                {
                        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
                        {13, 14, 15, 16}
                },
                {
                        {1, 2, 3, 4, 5, 6, 7, 8},
                        {9}
                },
                {
                        {1, 2, 3, 4}
                },
                {
                        {1}
                }
        };

        // Contains matrizes.
        int[][][] testCasesOutput = {
                {
                        {1, 2, 3, 4, 5},
                        {16, 17, 18, 19, 6},
                        {15, 24, 25, 20, 7},
                        {14, 23, 22, 21, 8},
                        {13, 12, 11, 10, 9}
                },
                {
                        {1, 2, 3, 4},
                        {12, 13, 14, 5},
                        {11, 16, 15, 6},
                        {10, 9, 8, 7}
                },
                {
                        {1, 2, 3},
                        {8, 9, 4},
                        {7, 6, 5}
                },
                {
                        {1, 2},
                        {4, 3}
                },
                {
                        {1}
                }
        };

        int[][] outValue;
        for (int inIndex = 0, outIndex = 0; inIndex < testCasesInput.length; inIndex++, outIndex++) {
            outValue = MatrixRingsRotation.reassembleRingsOnMatrix(testCasesInput[inIndex]);
            System.out.println(String.format("@Test: reassembleRingsOnMatrix():\nrings: %s\nmatrix: %s",
                    Arrays.deepToString(testCasesInput[inIndex]), Arrays.deepToString(outValue)));
            assertArrayEquals(testCasesOutput[outIndex], outValue);
        }
    }

    @Test
    public void rotateMatrix() throws Exception {
        // Contains the matrix to have its rings rotated.
        int[][][] testCasesInputMatrizes = {
                {
                        {1, 2, 3, 4, 5},
                        {16, 17, 18, 19, 6},
                        {15, 24, 25, 20, 7},
                        {14, 23, 22, 21, 8},
                        {13, 12, 11, 10, 9}
                },
                {
                        {1, 2},
                        {4, 3}
                },
                {
                        {1}
                }
        };
        // Contains the quantity of positions to rotate the rings of the correspondent matrix in "testCasesInputMatrizes".
        int[][] testCasesInputPositions = {
                {0, 1, 2, 4, 8, 16, 20},
                {0, 1, 2},
                {0, 1, 2}
        };

        // Contains the matrizes in "testCasesInputMatrizes" rotated by the quantity of possitions in "testCasesInputPositions".
        int[][][][] testCasesOutput = {
                {
                        {
                                {1, 2, 3, 4, 5},
                                {16, 17, 18, 19, 6},
                                {15, 24, 25, 20, 7},
                                {14, 23, 22, 21, 8},
                                {13, 12, 11, 10, 9}
                        },
                        {
                                {2, 3, 4, 5, 6},
                                {1, 18, 19, 20, 7},
                                {16, 17, 25, 21, 8},
                                {15, 24, 23, 22, 9},
                                {14, 13, 12, 11, 10},
                        },
                        {
                                {3, 4, 5, 6, 7},
                                {2, 19, 20, 21, 8},
                                {1, 18, 25, 22, 9},
                                {16, 17, 24, 23, 10},
                                {15, 14, 13, 12, 11}
                        },
                        {
                                {5, 6, 7, 8, 9},
                                {4, 21, 22, 23, 10},
                                {3, 20, 25, 24, 11},
                                {2, 19, 18, 17, 12},
                                {1, 16, 15, 14, 13}
                        },
                        {
                                {9, 10, 11, 12, 13},
                                {8, 17, 18, 19, 14},
                                {7, 24, 25, 20, 15},
                                {6, 23, 22, 21, 16},
                                {5, 4, 3, 2, 1}
                        },
                        {
                                {1, 2, 3, 4, 5},
                                {16, 17, 18, 19, 6},
                                {15, 24, 25, 20, 7},
                                {14, 23, 22, 21, 8},
                                {13, 12, 11, 10, 9}
                        },
                        {
                                {5, 6, 7, 8, 9},
                                {4, 21, 22, 23, 10},
                                {3, 20, 25, 24, 11},
                                {2, 19, 18, 17, 12},
                                {1, 16, 15, 14, 13}
                        }
                },
                {
                        {
                                {1, 2},
                                {4, 3}
                        },
                        {
                                {2, 3},
                                {1, 4}
                        },
                        {
                                {3, 4},
                                {2, 1}
                        }
                },
                {
                        {
                                {1}
                        },
                        {
                                {1}
                        },
                        {
                                {1}
                        }
                }
        };

        int[][] outValue;
        for (int inIndex = 0; inIndex < testCasesInputMatrizes.length; inIndex++) {
            int[] testCasesInputPosition = testCasesInputPositions[inIndex];
            for (int posIndex = 0; posIndex < testCasesInputPosition.length; posIndex++) {
                int[][] testCaseInputMatrix = testCasesInputMatrizes[inIndex];
                int testCaseInputPosition = testCasesInputPosition[posIndex];
                outValue = MatrixRingsRotation.rotateMatrix(testCaseInputMatrix, testCaseInputPosition);
                System.out.println(String.format("@Test: rotateMatrix():\nmatrix: %s\npositions: %d\nrotated: %s",
                        Arrays.deepToString(testCaseInputMatrix), testCaseInputPosition, Arrays.deepToString(outValue)));
                assertArrayEquals(testCasesOutput[inIndex][posIndex], outValue);
            }
        }
    }
}