import os
import json
import queue
import time
from random import randint
from flask import Flask, request, render_template, stream_with_context
from langchain_openai import ChatOpenAI

memory = []
app = Flask(__name__)
message_queue = queue.Queue()

@app.route("/", methods=['GET'])
def home():
    return render_template("index.html")

@app.route('/ask', methods=['POST'])
def chat_input():
    user_input = request.form.get("message")
    message_queue.put(user_input)
    return "Success", 204

@app.route('/stream')
def stream():
    def message_stream():
        global new_conversation

        while True:
            if not message_queue.empty():
                user_message = message_queue.get()
                userMessageTemplate = render_template('userMessage.html', user_message=user_message)
                userRes = f"""data: {userMessageTemplate}\n\n"""
                yield userRes

                apiModelDetails = getModelApiDetails()
                llm = ChatOpenAI(base_url=apiModelDetails['apiBase'], model=apiModelDetails['modelName'], api_key=apiModelDetails['apiKey'])
                promptMessages = memory.copy()
                promptMessages.append({ 'role':'user', 'content': user_message })
                stream = llm.stream(promptMessages)

                current_response_id = f"getblock{randint(67, 999999)}"
                message = ""
                hx_swap = False
                for chunk in stream:
                    try:
                        word = chunk.content
                        message += word.replace("\n", "<br>")
                        ai_message = message
                        res = f"""data: <p class="mt-4 overflow-auto" id="{current_response_id}" {"hx-swap-oob='true'" if hx_swap else ""}>{ai_message}</p>\n\n"""
                        hx_swap = True
                        yield res
                    except Exception as e:
                        print(e)
                        return e
                storeInMemory(user_message, message)

                    
    return app.response_class(stream_with_context(message_stream()), mimetype="text/event-stream")

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