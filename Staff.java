
package NewBank;

/* 
    CA2 OOP module
    Dorset College 2020
    William Souza
    20268
   */


public class Staff {

    Matrix_Activities bank;

    public Staff(Matrix_Activities Branch) {
        this.bank = Branch;
    }

    public Matrix_Activities getBank() {
        return bank;
    }
}
