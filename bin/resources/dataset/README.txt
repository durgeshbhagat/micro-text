INPUT DATA FORMATS:
Each training data is contained in the file training.txt in the following format:
    <tweetid label tweettext>
where the 'label' is either 'Sports' or 'Politics' for the tweet identified by 'tweetid' and has text 'tweettext'

Each validation data is contained in the file validation.txt in the following format:
    <tweetid tweettext>
The validation data has no 'label'. The task is to assign label to this data(either 'Sports' or 'Politics')

Only the data contained in training.txt is to be used for the training of the model/algorithm. No external data sources
outside training.txt is allowed. Also any other field other than 'label' or 'tweettext' is STRICTLY FORBIDDEN. 

OUTPUT DATA FORMAT:
Please create an output file with the following format:
    <tweetid label>
where the 'label' is either 'Sports' or 'Politics' assigned to the tweet with identifier 'tweetid' by your algorithm.




