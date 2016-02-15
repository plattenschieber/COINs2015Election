# Query for table data, with an optional start index.
def queryTableData(service, project, dataset, table, startIndex=0):
  tableDataJob = service.tabledata()
  try:
    queryReply = tableDataJob.list(projectId=project,
                                   datasetId=dataset,
                                   tableId=table,
                                   startIndex=startIndex).execute()
    print 'Printing table %s:%s.%s' % (project, dataset, table)

    # When we've printed the last page of results, the next
    # page does not have a rows[] array.
    while 'rows' in queryReply:
      printTableData(queryReply, startIndex)
      startIndex += len(queryReply['rows'])
      queryReply = tableDataJob.list(projectId=project,
                                     datasetId=dataset,
                                     tableId=table,
                                     startIndex=startIndex).execute()
  except HttpError as err:
    print 'Error in querytableData: ', pprint.pprint(err.content)

def printTableData(data, startIndex):
  for row in data['rows']:
    rowVal = []
    for cell in row['f']:
        rowVal.append(cell['v'])
    print 'Row %d: %s' % (startIndex, rowVal)
    startIndex +=1
  
