package com.packtpub.e4.clock.ui;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ClockWidget extends Canvas {

	private final Color color;
	private int offset;

	public ClockWidget(Composite parent, int style, RGB rgb) {
		super(parent, style);
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				ClockWidget.this.paintControl(e);
			}
		});
		this.color = new Color(parent.getDisplay(), rgb);
		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				if (color != null && !color.isDisposed())
					color.dispose();
			}
		});
		new Thread("TickTock") {
			public void run() {
				while (!ClockWidget.this.isDisposed()) {
					ClockWidget.this.getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							if (ClockWidget.this != null && !ClockWidget.this.isDisposed())
								ClockWidget.this.redraw();
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						return;
					}
				}
			}
		}.start();
	}

	public Point computeSize(int w, int h, boolean changed) {
		int size;
		if (w == SWT.DEFAULT) {
			size = h;
		} else if (h == SWT.DEFAULT) {
			size = w;
		} else {
			size = Math.min(w, h);
		}
		if (size == SWT.DEFAULT) {
			size = 50;
		}
		return new Point(size, size);
	}

	public void paintControl(PaintEvent e) {
		e.gc.drawArc(e.x, e.y, e.width - 1, e.height - 1, 0, 360);
		Date date = new Date();
		int seconds = date.getSeconds();
		// Color colorBlue = e.display.getSystemColor(SWT.COLOR_BLUE);
		int arc = (15 - seconds) * 6 % 360;
		e.gc.setBackground(color);
		e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arc - 1, 2);
		// hour hand
		e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
		int hours = new Date().getHours() + offset;
		arc = (3 - hours) * 3 % 360;
		e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arc - 5, 10);
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
