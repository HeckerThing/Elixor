#include <skylix/elixor/lexer/Lexer.hxx>
#include <skylix/elixor/strings/Strings.hxx>
#include <iostream>
#include <vector>

using namespace std;
using namespace Skylix::Elixor;

vector<Token> Lexer::GetTokens() {
    return this->tokens;
}

Lexer::Lexer(const string data) {
    this->data = data;

    auto chars = Strings::Split(data, "");

    int column = 0;
    int index = 0;
    int line = 0;

    for (auto& symbol : chars) {
        if (symbol == "\n") {
            line++;
            column = 0;
        }

        Token token = {};

        token.column = column;
        token.line = line;
        token.index = index;
        token.value = symbol;

        cout << "Token: " << token.value << " at " << token.line << ":" << token.column << endl;

        tokens.push_back(token);

        column++;
        index++;
    }
}
