package com.gradescope.HW2;

import bridges.base.Color;
import bridges.base.ColorGrid;

import java.util.Arrays;
import java.util.stream.StreamSupport;

public class Scene {
	private final int maxMarks;
	private final Color backgroundColor;
	private final Mark[] marks;
	private int size;

	/**
	 * Constructor
	 * @param maxMarks The maximum number of Marks this Scene can hold
	 * @param backgroundColor The background color of this Scene
	 */
	public Scene(int maxMarks, Color backgroundColor) {
		this.maxMarks = maxMarks;
		this.backgroundColor = backgroundColor;
		this.marks = new Mark[maxMarks];

		size = 0;
	}

	/**
	 * Adds a Mark to this Scene
	 * @param mark The Mark to add
	 */
	public void addMark(Mark mark) {
		if (isFull()) throw new IllegalStateException("No room to add more Marks");
		System.out.println("Before: " + Arrays.toString(marks));
		marks[size] = mark;
		size++;

		System.out.println("After: " + Arrays.toString(marks));
		System.out.println();
	}

	/**
	 * Deletes the Mark at the given index
	 * @param index The index of the Mark to delete
	 */
	protected void deleteMark(int index) {
		if (index < 0 || index >= size) throw new IllegalArgumentException("Index out of bounds");

		for (int i = index; i < size - 1; i++) {
			marks[i] = marks[i + 1];
		}

		size--;
	}

	/**
	 * Returns the Marks in this Scene
	 * @return The Marks in this Scene
	 */
	public Mark[] getMarks() {
		return marks;
	}

	/**
	 * Helper method: returns true if the Scene is full
	 * @return true if the Scene is full
	 */
	private boolean isFull() {
		return size >= maxMarks;
	}

	/**
	 * Returns the number of Marks in this Scene
	 * @return The number of Marks in this Scene
	 */
	public int length() {
		return size;
	}

	/**
	 * Deletes all Marks of a given color from this Scene
	 * @param color: the Color to delete
	 */
	public void deleteMarksByColor(Color color) {
		int i = 0;
		while (i < size) {
			System.out.println("Testing index " + i);
			System.out.println("Checking for color: " + color);
			System.out.println("Mark being tested: " + marks[i]);
			System.out.println("Mark's color: " + marks[i].color);

			if (marks[i].color.equals(color)) deleteMark(i);
			i++;
		}
	}

	/**
	 * Draws this Scene onto a ColorGrid
	 * @param colorGrid The ColorGrid to draw the scene with
	 */
	public void draw(ColorGrid colorGrid) {
		for (int i = 0; i < colorGrid.getWidth(); i++) {
			for (int j = 0; j < colorGrid.getHeight(); j++) {
				colorGrid.set(j, i, backgroundColor);
			}
		}

		for (int i = 0; i < size; i++) {
			marks[i].draw(colorGrid);
		}
	}
}
