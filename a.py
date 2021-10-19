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
    print(a)
    print(b)
    print(c)
    print(d)
    print(e)
    print(f)

    #
    #balls_left = 120 - (overs*6)
    #wickets_left = 10 -wickets
    #crr = current_score/overs

    #input_df = pd.DataFrame(
     #{'batting_team': [batting_team], 'bowling_team': [bowling_team],'city':city, 'current_score': [current_score],'balls_left': [balls_left], 'wickets_left': [wickets], 'crr': [crr], 'last_five': [last_five]})
    #result = pipe.predict(input_df)
    

    return jsonify({'placement':str(int(result[0]))})

if __name__ == '__main__':
    app.run(debug=True)