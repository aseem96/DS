
#include <stdlib.h>
#include <stdio.h>
#include <rpc/rpc.h>
#include "fact.h"

main(argc, argv)
int argc;
char **argv;
{
	CLIENT *cl;	/* rpc handle */
	char *server;
	int *num;
	int *result;

	if (argc != 2) {
		fprintf(stderr, "usage:  %s hostname\n", argv[0]);
		exit(1);
	}
	server = argv[1];	/* get the name of the server */

	num = (int *)malloc(sizeof(int));
	result = (int *)malloc(sizeof(int));

	/* create the client handle */
	if ((cl=clnt_create(server, FACT_PROG, FACT_VERS, "udp")) == NULL) 
	{
		/* failed! */
		clnt_pcreateerror(server);
		exit(1);
	}

	printf("\nEnter the number whose factorial is to be calculated: ");
	scanf("%d",num);

	/* call the procedure bin_date */
	if ((result=fact_1(num, cl))==NULL) {
		/* failed ! */
		clnt_perror(cl, server);
		exit(1);
	}
	printf("Factorial returned from server is : %d\n", *result);

	clnt_destroy(cl);	/* get rid of the handle */
	exit(0);
}