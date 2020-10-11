# Lab Report 4: Fragment and MVVM
In this lab report I made a grocery list app with fragments and MVVM using LiveData

[Repository](https://github.com/hiradevina/learn-tktpl-1706979221/tree/lab-4) 

![Fragment1](https://lh3.googleusercontent.com/8GcEpR6GuMCy9F1BfSvrg5pyg04GQIK8CAW8pfIG7ysMM_-BBNqhQvYPwG-DY2qe2owplmtPjwLaSmMwfq_INMFTM6uItV0B7ijjsFsmdzNZV8b29TXDZur2BJ_m8Wbj3cIcHbi7dew0QIUqo3arsh51OsfhbAukaLfzcUg6CUV3fh9mdTkYiNpZVcC5i_Md34BfVVWpWB7sMCMZxhS5z4ARgwz8sQj2IKWFyQYixqv1DrZPBoaBst18tbBCnI4l43OBm1a1uaI0ydReXludD13CkxPUDSpS8sJHk5MfSBDt1NYABNdtPZvqe0_lJKkzOfg8nhBB-6RDfgP8-e7ofy2ey3Wnnw4I8aeOnhAUDtFaqgcO2rS37qG4Q3AQ9fDqnzyLFK0F-g3wFialnYBXSf4Je0PEMpL0MjkIHG78qsXB3e2vOZi_z-6HRWqVn6PdrNSAxXAeF5SK_ivjeFMPJjcot5ppFzoXubiGDdZsEe35wF-jgURlApO0AbQem_NTQ2vmSdhOy1piOHv8dYSUDGbmArtqvT6NSrpF2XGJKeMQ8C-y91pnHbfbWcq6HEzdnJggNdbEavBDmc0JPNVYDAc1rY7DGHIUDxec-alV5h-YMqjIrbTO1NoErLc8qyCIqWPxHqNoWa1NLiXY8_W8ClNtl2_OoEVbXoV037KNX3VSc3LWHzNydcgpK2bh=w165-h343-no?authuser=0)

![Fragment2](https://lh3.googleusercontent.com/kW5WYl_1f20-eRh0SHBSEpLPJNRD2kwQ6UTjNzXcYtJtDb8ZiPoIsDwPzoEtW9nyoW1qNY-l0fl7XdA3rLBc2QOaDwSgOaC_jWCz0QFDuxBuzXFLFs0u1TytWF-XP7twfJhiGAC4Sg2BWQP0LjMRE4K44NDALz9_6zSFTbu6NdUeBM0GSbuOzotv_DQXao5cWgwigJnzt-D6d3GEyfKshGIWbBkIsrxFsrKGtExIK7pt46LtEE4moHHUAVHpA8Ls1XFcp1n7UVZNDnr9ENUVbx_fj8izNKhG38iSiK0IF7ZyPilgrlzhf9PFno1-NT3Ngc25QymMWXia6XWvkNGnECUT-XJ2tfsha1fAOKu2q9oynlAHlXZKpqdpSWx3PdYjpbDv7kKnBmPlFRECFQnOj4f7oOuU-urcqDo2ZfAd9mdp0fvi6JzSY3iwoWbkFjCs22nxfO1afbCtMOtquS8lHSsEyqWfmFqzh8XkBOyi3R8p_ImmMotZ-pcr3IFnXBaKWJ_zGl44z1WxBj0SMI3pfwQpryoc4R-pF9gVZ-4CQBh7FpugmI_2Cxz_AyH3pdMNW98nVf40Vs0Ta7ANdUB3RZHV6QDaJyxC1EN_Lr9ncS3NlI1CTn8daH4RV3XJSg1RCOp9pb1-b_NO4ONj93_GSpMn-n_7yzZj7U5wqfS_Vdw9IHPjLh5UWhk7F9Xw=w397-h846-no?authuser=0)
## Directory and Files
1. **/data**

For all Groceries data storing
- FakeDatabase: store all DAOs
- FakeGroceryDao: fake Grocery table using LiveData and actions allowed for accessing the Grocery table
- Grocery
- GroceryRepository: bridge between DAO and UI (through GroceryViewModel)
2. **/ui/groceries**

For UI related and data access
- GroceryActivity: main activity, listen to changes from FakeGroceryDao through GroceryViewModel
- GroceryFragment: fragment used to show the grocery
- GroceryFragment2: fragment used to add the grocery, store new Grocery data through GroceryViewModel
- GroceryViewModel: bridge between DAO (through GroceryRepository) and UI
- GroceryViewModelFactory: prevent GroceryViewModel from created more than once
3. **/utilities**

For dependency injection
- InjectorUtils: dependency injection for VM Factory