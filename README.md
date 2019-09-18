# iClicker-Simulator
Simulates an iClicker exam/test

The parties involved in this part are 
(a) a server (running on the instructor's computer) that communicates to nodes to connect 
(b) nodes (apps running on students' phones or other mobile devices).
The basic functionality that your program must support is a JOIN command from nodes -- the server must log this with event with the time (at the server), and some ANSWER commands from the nodes. The server must log these as well. You may assume that the server does not have to send questions. The instructor does this on the board or projector. Also assume for simplicity that the instructor does not close the window for accepting the answer to a question before opening it for the next one, so a student can answer questions in the wrong order (in reality the UI would not allow this). You need not implement a fancy UI for this assignment. Assume also that all questions are multiple choice (choices A-E, one choice sent for each question) and questions have numbers. You may assume that if a student answers a question more than once, their last answer will be recorded. At the end of the class, the user (instructor) manually stops the program (not with control-C!) and then the server must close all sockets and output the log in the following format:

Student1: join time
q1: answer
q2: answer
....

Student2: join time
.....

Student 3: .....
The parts of this assignment are:
Design the basic architecture using a client-server paradigm
Work out the basic UI for client and server so that each can act as described above
Implement and test the program with one server and 3 nodes
We will test this code with 3 students and 3 questions.

