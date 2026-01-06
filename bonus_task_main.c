#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "evaluateRPN.h"

// Επιστρέφει το επίπεδο προτεραιότητας ενός τελεστή
int precedence(char op) {
    switch (op) {
        case '^': return 3;
        case '*': case '/': case '%': return 2;
        case '+': case '-': return 1;
        default: return 0;
    }
}

// Ελέγχει αν ένας χαρακτήρας είναι τελεστής
int is_operator(char c) {
    return c=='+' || c=='-' || c=='*' || c=='/' || c=='^' || c=='%';
}

// Μετατρέπει μια μαθηματική παράσταση από ενδοθεματική σε μεταθεματικη
void infix_to_postfix(char infix[], char postfix[]) {
    stack_type stack;
    initialize(&stack);
    int i = 0;
    char token, temp[3], num[50];
    int numIndex = 0;

    while ((token = infix[i++]) != '\0') {
        if (isspace(token)) continue;

        if (isdigit(token)) {
            num[numIndex++] = token;
            while (isdigit(infix[i]) || infix[i] == '.') {
                num[numIndex++] = infix[i++];
            }
            num[numIndex] = '\0';
            numIndex = 0;
            strcat(postfix, num);
            strcat(postfix, " ");
        }
        else if (token == '(') {
            push(&stack, token);
        }
        else if (token == ')') {
            stack_element_type op;
            while (!is_empty(stack)) {
                pop(&stack, &op);
                if (op == '(') break;
                sprintf(temp, "%c ", (char)op);
                strcat(postfix, temp);
            }
        }
        else if (is_operator(token)) {
            stack_element_type topOp;
            while (!is_empty(stack)) {
                top(stack, &topOp);
                if (is_operator(topOp) &&
                    ((precedence(topOp) > precedence(token)) ||
                     (precedence(topOp) == precedence(token) && token != '^'))) {
                    pop(&stack, &topOp);
                    sprintf(temp, "%c ", (char)topOp);
                    strcat(postfix, temp);
                } else break;
            }
            push(&stack, token);
        }
        else {
            printf("Invalid character: %c\n", token);
            exit(1);
        }
    }

    stack_element_type op;
    while (!is_empty(stack)) {
        pop(&stack, &op);
        sprintf(temp, "%c ", (char)op);
        strcat(postfix, temp);
    }
}

// Κύριο Πρόγραμμα
int main() {
    char infix[MAX_CHARACTERS];
    char postfix[MAX_CHARACTERS] = "";

    printf("Enter a mathematical expression (infix form): ");
    fgets(infix, MAX_CHARACTERS, stdin);
    infix[strcspn(infix, "\n")] = '\0'; 

    infix_to_postfix(infix, postfix);

    printf("\nPostfix form: %s\n", postfix);

    double result = evaluateRPN(postfix);
    printf("Result: %.2f\n", result);

    return 0;
}


