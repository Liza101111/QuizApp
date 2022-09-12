package main;

import model.Questions;
import persistence.DaoQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Questions q = new Questions();
        q.setQuestionId(5);
        q.setQuestion("Which of these is the most popularly used class as a key in a HashMap?");
        q.setOption1("A. String");
        q.setOption2("B. Integer");
        q.setOption3("C. Double");
        q.setOption4("D. All of the above");
        //q.setQuestionId(2);

        DaoQuestions dq = new DaoQuestions();
        //dq.saveQuestion(q);
        // dq.updateQuestion(q);
        //dq.deleteQuestion(1);
     /*  Questions qResult = dq.searchQuestion(1);
        System.out.println(qResult.toString());*/

       /* ArrayList<Questions> result = dq.listAllQuestions();
        for(Questions questions:result){
            System.out.println(questions.toString());
        }
*/
        Quiz quiz = new Quiz();
        quiz.quizLogic();



    }

}

class Quiz{

    Scanner sc = new Scanner(System.in);
    int countCAns = 0;
    int countWAns = 0;

    public void quizLogic(){

        Questions q1 = new Questions(1,"The root interface of Java collection framework hierarchy is –","A. Collection", "B. Root","C. Collections","D. List/Set");
        Questions q2 = new Questions(2,"HashSet internally uses?","A. Set","B. HashMap","C. List","D. Collection");
        Questions q3 = new Questions(3,"Which of those is synchronized?","A. ArrayList","B. LinkedList","C. Vector","D. None of the above");
        Questions q4 = new Questions(4,"After resizing, size of ArrayList is increased by :","A. 200%","B. 50%","C. 100%","D. None of these");
        Questions q5 = new Questions(5,"Which of these is the most popularly used class as a key in a HashMap?","A. String","B. Integer","C. Double","D. All of the above");
        Map<Questions,Character> hashMap = new HashMap<>();
        hashMap.put(q1,'D');
        hashMap.put(q2,'A');
        hashMap.put(q3,'C');
        hashMap.put(q4,'B');
        hashMap.put(q5,'B');

        for(Map.Entry<Questions,Character> map : hashMap.entrySet()){
            System.out.println(map.getKey().getQuestion());
            System.out.println(map.getKey().getOption1());
            System.out.println(map.getKey().getOption2());
            System.out.println(map.getKey().getOption3());
            System.out.println(map.getKey().getOption4());

            System.out.println("Enter your answer: ");
            Character answer = sc.next().charAt(0);
            int correctAns = Character.compare(answer, map.getValue());
            if(correctAns == 0){
                System.out.println("Correct");
                countCAns++;
            } else{
                System.out.println("Wrong");
                countWAns++;
            }
        }
        System.out.println();
        System.out.println("*****Result******");
        System.out.println("Correct Answer(s): " + countCAns);
        System.out.println("Wrong Answer(s): " + countWAns);
        System.out.println("Percentage : " + (100*countCAns)/hashMap.size());




    }
}
