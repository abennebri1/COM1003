package uk.ac.sheffield.com1003.assignment;

import uk.ac.sheffield.com1003.assignment.codeprovided.*;
import java.util.*;

/**
 * SKELETON IMPLEMENTATION
 */
public class QueryParser extends AbstractQueryParser {

    // Default implementation to be provided
    @Override
public List<Query> readQueries(List<String> queryTokens) throws IllegalArgumentException {

    int counter = 0;

    int noOfLines = 0;

    int lineNum = 0;

    int A = 0;

    int LeagueIndex = 0;

    ArrayList<Query> QueriesList = new ArrayList<Query>();

    List<SubQuery> subQueriesList = new ArrayList<>();

    lineNum++;

    for (int i = 0; i < queryTokens.size(); i++)
    {
        if ("select".equals(queryTokens.get(i)))
        {

            noOfLines++;

        }
    }
    //For loop find the number of times the word 'select' is used within the queries file, which equates to the number of lines and is stored as such
    for(int x = 0; x < noOfLines; x++ )
    {
        subQueriesList.clear();

        LeagueIndex = counter;

        boolean MultipleLeagues = false;

        int lineSubqueries = 0;

        int lineCount = 0;

        int NumSubQueries = 0;

        String LeagueName="";

        A = counter;
        
        counter++;

        while("select".equals(queryTokens.get(counter)) == false)
        {
            if(lineNum == noOfLines)
            {
                counter -= 1;

                lineCount = queryTokens.size() - counter;

                break;
            }
            //If statement breaks the loop if it reaches the last line
            lineCount++;

            counter++;
        }

        NumSubQueries = (lineCount - 3)+1;

        if ("epl".equals(queryTokens.get(LeagueIndex)))
        {
            LeagueName = "English Premier League";

            NumSubQueries--;

            if (("or".equals(queryTokens.get(LeagueIndex + 1))) )
            {
                LeagueName = "All leagues";

                MultipleLeagues = true;

                NumSubQueries-=2;
            }
        }

        else if ("liga".equals(queryTokens.get(LeagueIndex)))
        {
            LeagueName = "La Liga";

            NumSubQueries--;

            if ("or".equals(queryTokens.get(LeagueIndex + 1)))  
            {
                LeagueName = "All Leagues";

                MultipleLeagues = true;

                NumSubQueries-=2;
            }
        }  
        //If statements check the league of the current query and store it to be used later to create a query
        lineSubqueries = (NumSubQueries+1)/4;
        
        PlayerProperty[] PropertyArray = new PlayerProperty[lineSubqueries];

        String[] OperatorArray = new String[lineSubqueries];

        double[] ValueArray = new double[lineSubqueries];

        A += 3;

        if (MultipleLeagues==true)
        {
            A += 2;
        }



        for (int i = 0; i < lineSubqueries; i++)
        {
            PropertyArray[i] = PlayerProperty.fromName(queryTokens.get(A));

            A++;

            OperatorArray[i] = queryTokens.get(A);

            A++;

            ValueArray[i] = Double.parseDouble(queryTokens.get(A));

            A++;

            subQueriesList.add(i, new SubQuery(PropertyArray[i], OperatorArray[i], ValueArray[i]));

            A++;
        }
        //For loop cycles through the queries in 3s and stores the Properties, Operators and Values, and then creates a subquery using them which is added to a subquery list
        League LeagueVariable = League.fromName(LeagueName);

        QueriesList.add(new Query(subQueriesList, LeagueVariable));

        lineNum++;

    }

    return QueriesList;
    }
}
//This code uses for loops to loop through each line in the queries file and find the subqueries for each line, which it then adds to a subquery list for each line, each of which is used to create a query, and each query is added to a query list which is returned.
