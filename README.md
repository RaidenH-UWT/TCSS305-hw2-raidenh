# TCSS305

Assignment 2

Raiden Hiland

Autumn 2024

## Assignment Overview
This project tasked us with implementing vehicle logic to pair with a
provided road map simulation frontend. The goal of this project was to
explore inheritance and learn how to abstract out behaviour so as to avoid
redundancy. Additionally, this project had us learn to compact down logic
for each of our vehicle classes to get the desired results as cleanly as
possible.

## Technical Impression:
The first thing I did was implement and abstract class implementing behaviour
from the interface that would be identical across all of my child Vehicle types.
I found the `abstract` modifier from a warning from CheckStyle, telling me that
my class named `AbstractVehicle` should be an abstract class. From there I did
some research and figured out how to use abstract classes and methods. I was
happy I found the suggested method of implementatino before we even covered it
in class. I did learn not to use abstract methods in class, so I modified mine
to match that.

I next implemented all of my tests, based on the behaviour described in the
interface. I did TruckTest first then copied the identical tests to my other
test classes. I made a couple mistakes in my tests that I found out about
later on, a couple of which were caused by copy-pasting and forgetting to
tune the tests to the vehicle being tested.

I then moved on to implementing my child vehicles. I started with ATV, as
suggested, because its logic is very simple. I then moved on to the Human
class, and realized that my `canPass()` methods were very similar. I tried
moving them up into the abstract class, but unfortunately I couldn't quite
abstract them because of the differences in logic. I then finished implementing
the rest of the vehicle types. The Bicycle class was by far the most complex.
Though its behaviour was fairly simple compared to others, say the Taxi,
it had the longest if statement because I had to check every direction for
Trails first. I actually ran into an issue here because I tried to do it more
compact by checking every direction in the map, but I forgot to ignore reverse
and had to swap it to an if.

My `reset()` method doesn't really live up to what I'd imagined for it. I
couldn't find any way to store the initial state of an object, so I just
made 3 variables in the abstract class that could reset the object to its
initial state. I thought about using an array, but dropped it because I was
storing different types. I'd love to know if there's a better way to do this.
After that it was all bugfixing, tuning, and tweaking. My last 11 commits were
just small changes.

I finished this project in 41 commits across 1 week.

## Unresolved problems in my submission:
I've got a couple warnings in my test classes which I've ignored because
they aren't graded. Most egregious is my TestTruck `testChooseDirection()`
method, which I finished off for code coverage and left rather messy.

## Citations and Collaborations:
None.

## Questions:
Is there a better way to store initial state of an object than just
storing the mutable variables inital states in variables? I'm not very
happy with my `reset()` method even though it's not that bad.
