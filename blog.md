# Task 3:
Other uses of observer patterns include:
EventListeners 

# State Pattern
## Task 1
States are Red, Yellow, Green for a traffic light
Actions are:
- reportState
- change

1) Open/Closed principle refers to software entities being closed for modification but opened for extension. Since all the code is retained in TrafficLight.java, new modifications will need to be made inside TrafficLight.java which will violate the Open/Closed principle. As such, state pattern programming allows us to add modifications by creating new classes

2) Strategy pattern refers to grouping related behaviours to a family of methods by implementing to an interface. While state pattern refers to implementing a Finite State Machine (FSM) via an interface consisting of certain states and actions. This problem is a state pattern because a traffic light can only exist in one state is a FSM