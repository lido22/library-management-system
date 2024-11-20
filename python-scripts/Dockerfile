FROM python:3.10-slim

WORKDIR /scripts

COPY ./requirements.txt /scripts

RUN pip install --no-cache-dir -r /scripts/requirements.txt

CMD ["tail", "-f", "/dev/null"]

#this line is to run the script
#docker exec -it python_scripts python generate_fake_data.py