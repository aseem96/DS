
#include <rpc/rpc.h>
#include "fact.h"

int *fact_1_svc(int *num, struct svc_req *rqstp)
{
	int temp = 1, i;

	for(i=1;i<=*num;i++)
		temp = temp * i;

	return &temp;
}