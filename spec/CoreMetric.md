# 👀 Core Exercise: Metric

# Background

In this lab, you will be using the observer and the strategy patterns to create a graph to display a _metric_.

> **What is a metric?**
>
> Think of a metric as a variable to monitor, when you have a system it can output all sorts of information that can be helpful when analysing how your system is performing. Metrics can help monitor things like performance, availability and reliability. For example if you have a server cluster that has 100 host machines they might report metrics such as `cpu.utilization`, `api.calls`, and `dropped.packets`.
>
> The image below is an example of what a dashboard showing metrics could look like. This sort of output and metrics in general can help developers notice problems or better understand why something broke.
>
> For example if your api.calls metric all of a sudden drops from 1000 calls a minute to 5 it could be a sign of a problem. You can then fix it without your users having to inform you, minimising the impact it has.
>
> If you want to learn more about metrics and observability you can read [this](https://iamondemand.com/blog/the-3-pillars-of-system-observability-logs-metrics-and-tracing/).
>
> ![](/images/Metrics.png)

In the case of our simple program, we have a system an `Emitter` which is determining the values of a sine wave but not doing anything with them. The aim of this lab is to graph these values.

---

To run the program as it is, go into [`Grapher.java`](/src/main/java/metrics/graphing/Grapher.java) and run the main function. In the beginning, it creates a window with a `Panel`. The `Panel` displays some default random data that it gets from [`Plot.java`](/src/main/java/metrics/Plot.java).

![](/images/PlotExample.png)

> All of the core tasks are independent of one another, so if you get stuck on one you can move to the next without it impacting your ability to complete it.

# WSL Prerequisites

> Skip this section if you are not using WSL

If you are using WSL, ensure you are using **WSL version 2** with **WSLg** (WSL GUI) enabled.

You can Google how to do this or use the following resources:

- [GitHub Microsoft WSLg](https://github.com/microsoft/wslg)
- [Run Linux GUI apps on the Windows Subsystem for Linux](https://learn.microsoft.com/en-us/windows/wsl/tutorials/gui-apps)

> If you are not using WSL2 or WSLg, when running the metrics lab, you will experience an issue similar to:
>
> ```
> No X11 DISPLAY variable was set, but this program performed an operation which requires it.
> ```

# Task 1) Observer Pattern

At the moment the data inside Plot is not changing and is just some random data. We want the data that is being generated by the Emitter to be shown on the panel and update the graph as it emits more data.

Use the observer pattern to add the Plot as an observer to the Emitter in the main function so that whenever it emits a metric Plot is updated.

In your update function, you will need to call if (panel != null) panel.repaint() to get the visualization to update.

> Below is a video of the expected output

[Lab05 metrics task 1](https://youtu.be/xKbvCJNUHSk)

> If you want to have a play around with what the `Emitter` emits you can change the code in `emitMetric()` to emit different things. For example you can change it to use `Math.tan()` instead of `Math.sin()`.

# Task 2) Strategy Pattern

When analysing metrics it can be overwhelming to see every data point that the system emits, so usually, they are displayed using a statistic. In the example image in the background section, you can see it is displaying a few different statistics such as the percentile. You can see the definitions of the ones used by [Amazon CloudWatch here](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/Statistics-definitions.html).

On the side of the graph, there are four options to click on `All Points`, `Average`, `Max` and `Sum`. Those buttons currently don't do anything.

Stop here and think about the best way to design your implementation of the strategy pattern:

- What are the different strategies?
- How do you dynamically change between strategies?

Implement your designed strategy pattern so the data changes when the buttons are pressed.

<details>
  <summary>Hint</summary>
  <ul>
    <li>
      Inside of <code>Panel</code> in <code>paintData()</code> before
      <code>emitterData</code> is printed out on the graph. Use the strategy pattern
      to update this data depending on which option is selected from the above
      options over a given time frame to create new data points.
    </li>
  </ul>

```java
private void paintData(Graphics2D g) {
  List<Double> emitterData = plot.getData();

  // TODO change data based on statistic

  double x = (double) (getWidth() - 2 * marg) / (emitterData.size() - 1);
  double scale = (double) (getHeight() - 2 * marg) / (Math.max(Math.abs(getMinValue(emitterData)), Math.abs(getMaxValue(emitterData))) * 2);
  paintGridLines(g, emitterData);
  g.setColor(Color.RED);

  // set points to the graph
  for (int i = 0; i < emitterData.size(); i++) {
      double x1 = marg + i * x;
      double y1 = getHeight() / 2 + emitterData.get(i) * scale;
      g.fill(new Ellipse2D.Double(x1 - pointWidth / 2, y1 - pointWidth / 2, pointWidth, pointWidth));
  }
}
```

For example:

```
emitterData = [0, 1, 2, 3, 4, 5, 2, 4, 7, 9, 1]
// Sum strategy over the time frame of 5 units
emitterData = [10, 27]
```

If the data doesn't fit nicely, ignore the remainder of the data points.

---

To update which strategy is used when the corresponding button is pressed, use the <code>valueChanged()</code> function inside of <code>Grapher</code> in the <code>createControlPanel()</code> function.

</details>

> Below is a video of the expected output
>
> The timeframe that the statistics are evaluated over in the example is 10 but you can make the timeframes whatever you want

[Lab05 metrics task 2](https://youtu.be/o2GhAIEdYwE)

# Task 3) Identify other instances of the Observer pattern

Throughout the source code, there are a few other examples of the observer pattern being used. Try and find them or at least identify where you think they might be being used. Add this to a blog post as well as any questions you have for your tutor/lab assistant about the lab.

# Task 4) Multiple Metrics (Choice)

At the moment the graph can only output one metric/Plot at a time. Extend the code so that it can handle more than one metric at a time this can be either a separate metric from the same emitter or a metric from a new emitter and plot them in different colours.