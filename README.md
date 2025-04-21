# Sudoku_Solver
# 🧩 Java Sudoku Solver

A console-based Sudoku puzzle solver implemented in **Java** that supports any square-sized grid (e.g., 4x4, 9x9, 16x16). The program takes a Sudoku puzzle as input and solves it using the **backtracking algorithm**.

---

## 📌 Features

- ✅ Solves any N x N Sudoku grid where N is a perfect square (e.g., 4, 9, 16)
- ✅ Input validation for rows and grid size
- ✅ Pretty-printed Sudoku board with box separators
- ✅ Recursive backtracking solver
- ✅ Checks for invalid puzzles before solving
- ✅ Lightweight and easy to run from the terminal

---

## 📸 Demo

```bash
Enter the grid size (must be a perfect square, e.g. 4, 9, 16): 9

Enter the Sudoku puzzle row by row (use 0 for empty cells):
Row 1: 5 3 0 0 7 0 0 0 0
Row 2: 6 0 0 1 9 5 0 0 0
Row 3: 0 9 8 0 0 0 0 6 0
...

Original Sudoku:
 5 3 . | . 7 . | . . .
 6 . . | 1 9 5 | . . .
 . 9 8 | . . . | . 6 .
-------------------------
 8 . . | . 6 . | . . 3
 4 . . | 8 . 3 | . . 1
 7 . . | . 2 . | . . 6
-------------------------
 . 6 . | . . . | 2 8 .
 . . . | 4 1 9 | . . 5
 . . . | . 8 . | . 7 9

Solved Sudoku:
 5 3 4 | 6 7 8 | 9 1 2
 6 7 2 | 1 9 5 | 3 4 8
 1 9 8 | 3 4 2 | 5 6 7
...
