from flask import Flask,request,jsonify
import numpy as np
import pickle
import pandas as pd
import numpy as np
import xgboost
from xgboost import XGBRegressor

#model = pickle.load(open('model.pkl','rb'))
pipe = pickle.load(open('pipe.pkl','rb'))

app = Flask(__name__)

@app.route('/')
def index():
    return "Hello world"

@app.route('/predict',methods=['POST'])
def predict():
    a = request.form.get('a')
    b = request.form.get('b')
    c = request.form.get('c')
    d = request.form.get('d')
    e = request.form.get('e')
    f = request.form.get('f')
    g = request.form.get('g')
    e=int(e)
    d=int(d)
    
    
    balls_left = 120 - (e*6)
    wickets_left = 10 -e
    crr = d/e

    input_df = pd.DataFrame(
     {'batting_team': [a], 'bowling_team': [b],'city':c, 'current_score': [d],'balls_left': [e], 'wickets_left': [e], 'crr': [crr], 'last_five': [g]})
    result = pipe.predict(input_df)
    

    return jsonify({'Run':str(int(result[0]))})

if __name__ == '__main__':
    print("st")
    app.run(host='0.0.0.0', port=80)