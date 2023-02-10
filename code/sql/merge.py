import os

dirPath = r"D:\Code\java\SGBlog\SQL" #所有txt位于的文件夹路径,自行修改
save_file = os.path.join(dirPath, 'aaa.sql')
suffix = '.sql'


files = os.listdir(dirPath)
res = ""
i = 0
for file in files:
    if file.endswith(suffix):
        i += 1
        # title = "第%s节 %s" % (i, file[0:len(file)-4])
        # title = "   "
        with open(os.path.join(dirPath, file), "r", encoding='utf-8') as file:
            content = file.read()
            file.close()
        # append = "\n%s\n\n%s" % (title, content)
        res += content
with open(save_file, "w", encoding='utf-8') as outFile:
    outFile.write(res)
    outFile.close()
print(len(res))

