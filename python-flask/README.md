# python-flask

This is a starter Python Flask project. 
The server will be listening to request on port `8000`,
so you can test the server in a browser accessing `http://localhost:8000` or via `cURL`.

## Running the application locally

### Setting up local virtual environment

Before running the app, you can create a virtual environment by running these commands:

```shell
python3 -m venv venv
. venv/bin/activate
pip install Flask
```

### Running the application locally

Once the virtual environment is activated, you can run it as a standalone app by running the following command from the root of the project:

```shell
FLASK_APP=web.py flask run --port 8000
```

### Access the local application

Access the local application by opening your browser using the URL [http://localhost:8000](http://localhost:8000) or use `cURL`.

## Tanzu Platform deployment

*TBD*
