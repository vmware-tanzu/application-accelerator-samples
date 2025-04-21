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
    print("=========== STARTING ASK FLOW ============")
    user_input = request.form.get("message")
    # current_response_id = f"gptblock{randint(67,999999)}"
    print("-----> PUTTING USER MESSAGE INTO QUEUE")
    message_queue.put(user_input)
    print(f"-----> QUEUE SIZE {message_queue.qsize()}")

    userMessageTemplate = render_template('userMessage.html', user_message=user_input)
    userRes = f"""data: {userMessageTemplate}\n\n"""
    yield userRes

    return "Success", 204

@app.route('/stream')
def stream():
    def message_stream():
        global new_conversation

        while True:
            if not message_queue.empty():
                print(".")

                print("----->  MESSAGE QUEUE NOT EMPTY")
                user_message = message_queue.get()
                userMessageTemplate = render_template('userMessage.html', user_message=user_message)
                userRes = f"""data: {userMessageTemplate}\n\n"""


                ### PROBLEM: At this yield point, sometimes it doesn't get past the yield. E.g., no "YIELDED"
                ###          message is ever logged. But sometimes it does move past it, but the browser never
                ###          receives anything in the stream.
                print(f"-----> YIELDING : {userRes}")
                yield userRes
                print("-----> YIELDED")
                print(f"-----> USER MESSAGE {user_message}")

                apiModelDetails = getModelApiDetails()
                print("-----> A")
                llm = ChatOpenAI(base_url=apiModelDetails['apiBase'], model=apiModelDetails['modelName'], api_key=apiModelDetails['apiKey'])
                print("-----> B")
                # message = request.form['message']
                promptMessages = memory.copy()
                print("-----> C")
                promptMessages.append({ 'role':'user', 'content': user_message })
                print("-----> D")
                stream = llm.stream(promptMessages)
                print("-----> Got a stream response...")
                # storeInMemory(message, answer.content)



                current_response_id = f"getblock{randint(67, 999999)}"
                print("-----> E")
                message = ""
                hx_swap = False
                print("-----> F")
                for chunk in stream:
                    print("-----> G")
                    try:
                        word = chunk.content
                        message += word.replace("\n", "<br>")
                        ai_message = message
                        # res = f"""data: <div class="mb-8" id="{current_response_id}" {"hx-swap-oob='true'" if hx_swap else ""}><div class="inline-block bg-red-300 text-stone-950 rounded-lg p-2 ml-auto"><b>{ user_message }</b></div><p class="mt-4 h-full overflow-auto">{ai_message}</p></div>\n\n"""
                        res = f"""data: <p class="mt-4 overflow-auto" id="{current_response_id}" {"hx-swap-oob='true'" if hx_swap else ""}>{ai_message}</p>\n\n"""
                        hx_swap = True

                        ### PROBLEM: At this yield point, the code continues to run (e.g., you always get a "YIELDED" message)
                        ###          But the browser doesn't seem to receive anything in its stream.
                        print(f"-----> YIELDING PARTIAL RESPONSE {res}")
                        yield res
                        print(f"-----> YIELDED")
                    except Exception as e:
                        print(e)
                        return e
                print("======= ENDING ASK FLOW =======\n\n")
                    
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
