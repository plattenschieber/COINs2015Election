import praw
from pprint import pprint

# setup a unique user agent to not get banned
r = praw.Reddit(user_agent='mac:COINs2015Election:v1.0.1 (by /u/plattenschieber)')
# this is the subreddit we are interested in
# TODO should be a dynamical list based on buzzwords and some manual ones where we know they are relevant for us 
subreddit = r.get_subreddit('SandersForPresident')

# get the hottest submissions in this reddit 
# TODO should be a lot more to generate some base data
for submission in subreddit.get_hot(limit=5):
    # we get a lot of more_comments structures which need to be resolved
    # the maximum of one time resolvement is at 200 comments hence the replace_more_comments call
    submission.replace_more_comments(limit=None, threshold=0)
    # flattening the tree since we don't care about the answering order
    flat_comments = praw.helpers.flatten_tree(submission.comments)
    for comment in flat_comments:
        # test if it contains relevant information about a candidate
        buzzwords = ["sander", "bernie", "sanders"]
        if any(buzz in comment.body.lower() for buzz in buzzwords):
            print(comment.body)
    # indicate finish of a submission
    print("\n ENDE")
