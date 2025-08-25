public class Simbolo {
    public String valor;

    public Simbolo(String valor) {
        this.valor = valor;
    }

    public boolean isOperando() {
        return this.valor.matches("\\d+");
    }

    public boolean isOperador() {
        return "+-*/".indexOf(this.valor) >= 0;
    }

    public boolean isAbreParenteses() {
        return this.valor.equals("(");
    }

    public boolean isFechaParenteses() {
        return this.valor.equals(")");
    }

    public int verificaPrioridade() {
        if (this.valor.equals("*") || this.valor.equals("/")) {
            return 2;
        } else if (this.valor.equals("+") || this.valor.equals("-")) {
            return 1;
        } else {
            return 0; // Para parênteses ou operandos
        }
    }

    @Override
    public String toString() {
        return this.valor;
    }
}
