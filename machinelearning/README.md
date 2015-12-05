# Machine Learning Tool for Analysis of voters

## Set Up

The best way to get started is by using maven. All dependencies are located in the pom.xml file and will be installed automatically when you execute `mvn compile` in the root folder. I recommend using IntelliJ IDEA as IDE, simply select "new project from existing sources" and select them pom.xml. IntellIJ will do the rest.

### Twitter API keys

In order to run the twitter fetcher, you need to enter your twitter API keys into the `twitter4j.properties` file located in the `resources` folder. Make sure you don't check your configuration file into the git repository!

## Running the program

Currently, the main ways to run the analysis are a) through the unit tests and b) using the main class. The main class currently only contains test code, but should be extended to perform different actions on input files.

## Tests

The project comes with unit tests for the critical classes. These can be executed by running `mvn test` or by running them in the IDE. The tests run on the sentences in `src/main/resources/TestData.txt`, feel free to add more test cases just make sure you adjust the expected counts accordingly.

## Known issues

This tool does not use natural language processing, it simply counts occurrences of certain words. Hence, it cannot distinguish between grammatically different uses of lexically identical words and will count ambiguous words in all the categories they belong to. Examples of this include *you* being counted as second person singular and plural and *for* being counted as preposition and conjunction. It's also not currently possible to reliably distinguish between the contraction *'s* on nouns from their possessive form. *'s* will only be counted as contraction when its use is not ambiguous (e.g. on prepositions). Contractions will also only be expanded when their use is non-ambiguous (e.g. *I'll* can always be expanded to *I will*, but *He'd* could be *He would* or *He had*.

## Packages

The tool has the following main packages:

* **lang** - deals with language processing
 * **lang.counting** - contains all the logic needed to count text occurrences using objects implementing the `Countable` interface
 * **lang.words** - contains the `WordType` enum, which defines `WordGroup`s to be counted
 * **lang.filters** - contains implementations of the `Filter` interface, which are applied to the text before counting words. Filters can be used to count characters without manipulation (e.g. `PunctuationFilter`) or to count and expand character groups before counting words (e.g. `ContractionFilter`)
* **twitter** - deals with connecting to twitter and interpreting twitter data
 * **twitter.stats** - contains tools to group twitter users and to perform statistical analysis on individual users, within groups of users, and between groups of users

## Reddit integration

To allow for easy analysis of reddit data, we should further abstract the **twitter.stats** package into a generic stats package, which is then be implemented for twitter stats and reddit stats. It probably makes sense to use the separate reddit fetcher and feed the tool prepared data from text files.
