import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String entrada = scan.nextLine();
        String[] simbolosString = entrada.split(" ");

        Queue<Simbolo> filaInfixa = new LinkedList<>();
        Queue<Simbolo> filaPosfixa = new LinkedList<>();
        Stack<Simbolo> pilhaConv = new Stack<>();

        for(String simbStr : simbolosString) {
            filaInfixa.add(new Simbolo(simbStr));
        }

        System.out.println(filaInfixa);

        Simbolo simbFila, simbPilha;

        while (!filaInfixa.isEmpty()) {
            simbFila = filaInfixa.poll();
            if (simbFila.isOperando()){
                filaPosfixa.offer(simbFila);
            } else if (simbFila.isAbreParenteses()) {
                pilhaConv.push(simbFila);
            } else if (simbFila.isOperador()) {
                while (!pilhaConv.isEmpty() && pilhaConv.peek().verificaPrioridade() >= simbFila.verificaPrioridade()) {
                    simbPilha = pilhaConv.pop();
                    filaPosfixa.offer(simbPilha);
                }
                pilhaConv.push(simbFila);
            } else if(simbFila.isFechaParenteses()) {
                while (!pilhaConv.peek().isAbreParenteses()) {
                    simbPilha = pilhaConv.pop();
                    filaPosfixa.offer(simbPilha);
                }
                pilhaConv.pop();
            }
        }
        while (!pilhaConv.isEmpty()) {
            simbPilha = pilhaConv.pop();
            filaPosfixa.offer(simbPilha);
        }

        System.out.println(filaPosfixa);
        System.out.println(computeExpression(filaPosfixa));


    }

    public static int computeExpression (Queue<Simbolo> posfixa)
    {
        Stack<Integer> pilhaCalc = new Stack<>();
        while (!posfixa.isEmpty())
        {
            Simbolo simbFila = posfixa.poll();
            if (simbFila.isOperando()) {
                pilhaCalc.push(Integer.parseInt(simbFila.toString()));
            } else {
                int operandoA = pilhaCalc.pop();
                int operandoB = pilhaCalc.pop();
                pilhaCalc.push(simbFila.calcularOperador(operandoA, operandoB));
            }
        }
        return  pilhaCalc.peek();
    }

}