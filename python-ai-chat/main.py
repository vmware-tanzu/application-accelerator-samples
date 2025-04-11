import os
from flask import Flask, request, render_template
from langchain_openai import ChatOpenAI

memory = []
app = Flask(__name__)

@app.route("/", methods=['GET'])
def home():
    return render_template("index.html")

@app.route("/ask", methods=['POST'])
def ask():
    baseUrl = os.environ.get('OPENAI_API_BASE_URL', 'https://api.openai.com/v1')
    chatModel = os.environ.get('OPENAI_MODEL', 'gpt-4o-mini')
    print(baseUrl)
    print(chatModel)

    llm = ChatOpenAI(base_url=baseUrl, model=chatModel)
    message = request.form['message']
    promptMessages = memory.copy()
    promptMessages.append({ 'role':'user', 'content': message })
    answer = llm.invoke(promptMessages)
    storeInMemory(message, answer.content)
    model = { 'messageIn': message, 'messageOut': answer.content }
    return render_template("chatMessage.html", model=model)

def storeInMemory(user, assistant):
    memory.append({ 'role': 'user', 'content': user })
    memory.append({ 'role': 'assistant', 'content': assistant})
    if len(memory) > 50:
        memory.pop(0)
        memory.pop(0)

if __name__ == "__main__":
    app.run(debug=True)