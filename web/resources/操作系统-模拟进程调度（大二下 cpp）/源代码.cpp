#include <iostream>
#include<ctime>
#include<stdlib.h>
#include<iomanip>

using namespace std;

void RRSchedule();//调度算法
void initTasks();//生成进程
void printAll();//打印所有进程信息
void print(int id);//打印进程号为id的信息
int menu();//打印菜单

struct Task {
    int id;//进程号
    int needtime;//所需CPU时间
    int wholetime;//总共所需时间
    int state;//状态字，1为执行完毕，0为待执行
    int priority;//优先级
};

bool isIni = false;//检测是否创建过进程
Task * tasks;//定义进程数组指针，以备动态创建数组
int numOfTask = 0;//创建的进程数

int main()
{
    int choice = menu();
    while (choice) {
        switch (choice) {
        case 1:
            initTasks();//选择1执行生成进程
            break;
        case 2:
            if (isIni)
                RRSchedule();//若已经生成进程才执行调度
            else
                cout << "请先初始化进程！" << endl;
            break;
        default:
            cout << "无效输入！\n请重新选择：\n";
        }
        choice = menu();
    }

    if (isIni) {//释放内存
        delete[]tasks;
        cout << numOfTask << "个进程已被释放！";
    }

    return 0;
}

void initTasks() {
    if (isIni) {
            delete []tasks;
            cout << numOfTask << "个进程已被释放！\n重新创建进程>>>>>>>";
            isIni = false;
    }
    
    cout << "请输入想要创建的进程数： " << endl;
    cin >> numOfTask;

    tasks = new Task[numOfTask];//动态创建进程数组

    cout << "请输入：>>>1.随机生成进程信息        2.手动输入进程信息" << endl;
    int isRandom;
    cin >> isRandom;

    if (isRandom == 1) {//随机生成进程信息
        srand(unsigned int(time(0)));
        for (int i = 0;i < numOfTask;i++) {
            tasks[i].id = i + 1;
            tasks[i].wholetime = tasks[i].needtime = rand() % 50;
            tasks[i].state = 0;
            tasks[i].priority = rand() % 100 + 100;
        }
    }
    if (isRandom == 2) {//手动输入进程信息
        for (int i = 0;i < numOfTask;i++) {
            cout << "正在创建进程" << i+1 << ">>>>>>>>>>>>>" << endl;
            cout << "请输入进程  总共所需运行时间：";
            cin >> tasks[i].needtime;
            tasks[i].wholetime = tasks[i].needtime;
            cout << "请输入进程  优先级：";
            cin >> tasks[i].priority;
            tasks[i].id = i + 1;
            tasks[i].state = 0;
        }
        cout << endl;
    }


    cout << "进程创建成功-----------------如下：" << endl;
    printAll();

    isIni = true;
}

void printAll() {
    cout << "进程号      还需运行时间       优先级       状态       总共所需运行时间" << endl << endl;
    for (int i = 0;i < numOfTask;i++) {
        cout << setw(3) << tasks[i].id << setw(20) << tasks[i].needtime << setw(13) <<tasks[i].priority << setw(10) << tasks[i].state<< setw(20) << tasks[i].wholetime<< endl;
    }
    cout << endl;
}

void print(int id) {
    cout << "进程号      还需运行时间       优先级       状态       总共所需运行时间" << endl;
    cout << setw(3) <<  tasks[id].id << setw(20) << tasks[id].needtime << setw(13) << tasks[id].priority <<setw(10) << tasks[id].state << setw(20) << tasks[id].wholetime << endl << endl;
}

int menu()
{
    cout << "进程调度：基于优先级的轮转进程调度" << endl << "请选择：\n1.(重新)生成进程\n2.执行调度\n0.退出程序\n";
    int choice;
    cin >> choice;
    return choice;
}

void RRSchedule() {
    cout << "基于优先级的轮转调度算法开始" << endl<<endl;
    int time;
    cout << "请输入时间片大小：" << endl;
    cin >> time;

    int priLevel;
    cout << "请输入优先级递减强度：" << endl;
    cin >> priLevel;

    int finishNum = 0;//计数已经完成执行的进程数

    while (finishNum < numOfTask) {
        int maxPri = -1;//获得优先级最高且状态值为1（即待执行）的进程
        for (int i = 0;i < numOfTask;i++)
            if (tasks[i].state == 0) {
                maxPri = i;
                break;
            }
        if (maxPri == -1)
            break;
        for (int i = 0;i < numOfTask;i++) {
            if ((tasks[maxPri].priority < tasks[i].priority)&&tasks[i].state == 0)
                maxPri = i;
        }

        if (tasks[maxPri].needtime <= time) {
            cout << "正在执行的是进程" << maxPri+1 << "----------------------分配CPU时间： " << tasks[maxPri].needtime <<"<<<<<<<执行成功"<< endl;
            tasks[maxPri].needtime = 0;
            tasks[maxPri].state = 1;
            finishNum++;
            cout << "进程" << maxPri + 1 << "执行完毕！" << endl;
        }
        else {
            tasks[maxPri].needtime -= time;
            cout << "正在执行的是进程" << maxPri+1 << "----------------------分配CPU时间： " << time <<"<<<<<<<执行成功"<< endl;
        }
        tasks[maxPri].priority -= priLevel;//递减优先级
        print(maxPri);
    }

    cout << "所有任务执行结束---------------------所有进程状态如下：" << endl;
    printAll();
}