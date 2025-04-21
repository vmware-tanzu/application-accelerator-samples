import os
import json
from flask import Flask, request, render_template
from langchain_openai import ChatOpenAI

memory = []
app = Flask(__name__)

@app.route("/", methods=['GET'])
def home():
    return render_template("index.html")

@app.route("/ask", methods=['POST'])
def ask():
    apiModelDetails = getModelApiDetails()
    llm = ChatOpenAI(base_url=apiModelDetails['apiBase'], model=apiModelDetails['modelName'], api_key=apiModelDetails['apiKey'])
    message = request.form['message']
    promptMessages = memory.copy()
    promptMessages.append({ 'role':'user', 'content': message })
    answer = llm.invoke(promptMessages)
    storeInMemory(message, answer.content)
    model = { 'messageIn': message, 'messageOut': answer.content }
    return render_template("chatMessage.html", model=model)

@app.route("/health")
def health():
    return "OK"

def getModelApiDetails():

    apiBase = os.environ.get('OPENAI_API_BASE_URL', 'https://api.openai.com/v1')
    apiKey = os.environ.get('OPENAI_API_KEY')
    modelName = os.environ.get('OPENAI_MODEL_NAME', 'gpt-4o-mini')
    details = {
        'apiBase': apiBase,
        'apiKey': apiKey,
        'modelName': modelName
    }

    vcapServices = os.environ.get('VCAP_SERVICES')
    if(vcapServices != None):
        vcapData = json.loads(vcapServices)
        apiBase = vcapData["genai"][0]["credentials"]["api_base"]
        apiKey = vcapData["genai"][0]["credentials"]["api_key"]
        modelName = vcapData["genai"][0]["credentials"]["model_name"]

    details['apiBase'] = apiBase
    details['apiKey'] = apiKey
    details['modelName'] = modelName
    return details

def storeInMemory(user, assistant):
    memory.append({ 'role': 'user', 'content': user })
    memory.append({ 'role': 'assistant', 'content': assistant})
    if len(memory) > 50:
        memory.pop(0)
        memory.pop(0)

if __name__ == "__main__":
    port = int(os.environ.get('PORT', 8080))
    app.run(debug=True, host='0.0.0.0', port=port)