# Satisfaction-Analyzer---DeisHacks-2022 Winner (Hidden Gem Award)

Programmers: James Kong & Benjamin Kamen

Researchers: Hannah Riseman, Chaodan Luo, Chieh-Ju Kuo

All Team Members: 

Hannah Riseman hannahriseman@brandeis.edu

Benjamin Kamen benjaminkamen@brandeis.edu 

James Kong jameskong@brandeis.edu/jameskong098@gmail.com

Chaodan Luo clduo@ucdavis.edu

Chieh-Ju Kuo ckuo@brandies.edu 

Overview: 
A GUI program that evaluates user satisfaction with Clippership Foundation/detects well being

- detects postiive/negative sentiment from words and phrases
- includes negation, multipliers, and other modifiers for accuracy

The Clipper Ship Foundation has put laser focus on supporting Youth ages 16-24 since 1979, through its annual grantmaking activities. Each year the Foundation receives more than 400 applications from nonprofit organizations in the greater Boston Area. While striving to improve the lives of our youth population, the Foundation has an interest in identifying models utilized in effective programs among nonprofit grantees, with a vision of more effective grant-making strategies to help nonprofits make even bigger impacts on the vulnerable youth population. Thus a Satisfaction Analyzer idea is born- a tool to efficiently detect how well the individual is doing. 


Measuring happiness and satisfaction through analyzing words and phrases is a common metric in positive psychology. Although most studies use surveys with scale-based questions, we decided to collect data in the form of a paragraph. The satisfaction analyzer algorithm can turn that into quantifiable data, similar to that derived from surveys. The words we used as well as the metrics were all based in well-known academic studies and international well-being surveys.


During the brainstorming phase, we wanted to include large datasets, natural language processing and machine learning algorithms so algorithms might categorize sentence sentiments better. However, we did not find suitable tools, so we didn’t include this at the end. But in the future version, we plan to use multiple large datasets, implement the packages so the algorithm can recognize and understand more complicated patterns of speech. 

Satisfaction Analyzer GUI Program: 

Our system is based on detecting positive and negative sentiments within the feedback paragraphs the foundation receives from recipients. There are certain statements that can indicate the opposite of their meaning as in a negated sentence where “not happy” or “not sad” would potentially trick the program but we make sure to account for this negation by checking for these negations and applying the necessary positive or negative counters. 


The magnitude number is our final holistic score, where positive numbers indicate a generally positive sentiment and negative numbers indicate a generally negative sentiment. Although the system is not perfect, we have worked to cover a lot of modifiers in order to make the score more accurate, including a multiplier that accounts for any quantitative metric of the program’s impact that the participant mentions. The program also outputs other useful information that can quickly give the user a general idea of the amount of positive and negative feelings by showing the stats of certain words/phrases found. 

*note: unresolved issue with exporting as a runnable jar file, text files do not get included with it, need to link with perhaps using inputstream
