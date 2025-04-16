# Python AI Chat

<center><img src="./.images/chat.png" alt="drawing" width="500"/></center>

This is an example of a simple web application that a user can use to chat with
an OpenAI-compatible LLM (including OpenAI itself and AI Server).

## Running the application

You will need a Python environment setup. The easiest way to do that (assuming
that you already have Python installed) is to use the venv module (from within
this project directory):

```script
python -m venv .venv
```

Then activate that environment:

```script
source .venv/bin/activate
```

Next you'll need to install the required dependencies:

```script
pip install -r requirements.txt
```

By default, the application works with OpenAI's API (at https://api.openai.com)
using the "gpt-4o-mini" model. But you can override these by setting environment
variables:

```script
export OPENAI_API_BASE_URL=https://some.other.openai.compatible.api/
export OPENAI_MODEL=some-other-model
```

You will also need to set the API key:

```script
export OPENAI_API_KEY=sk-ARm2s...
```

Finally, you can run the application:

```script
python main.py
```

Once started, open your web browser to http://localhost:5000 and chat with the
LLM.

## Running the application in CloudFoundry

To deploy and run the application in CloudFoundry, first create a new model as a service
from the AI Tile. For example, here's how to create a Llama 3.2 model named "myllama"

```
cf create-service genai llama3.2 myllama
```

Then simply push the application to CloudFoundry:

```
cf push
```

The manifest includes instructions for binding the application to a service named 
"myllama". If your model is named differently, edit manifest.yml and change the
service binding to match your model service's name.

Once the application has been deployed and started, open your web browser to the
host that is provided upon completion of `cf push`. From there, you may chat with
the LLM.




