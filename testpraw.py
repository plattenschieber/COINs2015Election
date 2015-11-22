import praw
from pprint import pprint

r = praw.Reddit(user_agent='mac:COINs2015Election:v1.0.1 (by /u/plattenschieber)')
subreddit = r.get_subreddit('SandersForPresident')
for submission in subreddit.get_hot(limit=5):
    pprint(help(submissions))
    # we get a lot of more_comments structures which need to be resolved
    # the maximum of one time resolvement is at 200 comments hence the replace_more_comments call
    submission.replace_more_comments(limit=None, threshold=0)
    # flattening the tree since we don't care about the answering order
    flat_comments = praw.helpers.flatten_tree(submission.comments)
    for comment in flat_comments:
        # test if it contains relevant information about a candidate
        # TODO should be a list of buzzwords in the future
        if "sander" in comment.body:
            print(comment.body)
    # indicate finish of a submission
    print("\n ENDE")
