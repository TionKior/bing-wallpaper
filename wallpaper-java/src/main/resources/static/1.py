# 爬取必应壁纸
import os
import re
import requests
import pymysql
import time
import random
import uuid


class WallPaper:
    def __init__(self, image_url, image_name, author, address):
        self.image_url = image_url
        self.image_name = image_name
        self.author = author
        self.address = address


def get_one_page(url):  # 解析给定url的网页源代码
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36'}
    response = requests.get(url, headers=headers)
    if (response.status_code == 200):  # 状态码200说明返回状态正确，状态码是404,403等其他代号则说明网页请求失败
        return response.text
    return None


def download(url, filename, author):  # 下载图片到本地文件夹
    filepath = r'./images/bingwallpaper/' + str(uuid.uuid1()) + '.jpg'  # 这里的路径可以更改自己的文件夹
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36'}
    if os.path.exists(filepath):  # 判断图片路径是否已经存在，如果存在就不保存了
        print(filename + '图片存在')
        return
    with open(filepath, 'wb') as f:  # 把图片以二进制形式写入到本地
        response = requests.get(url, headers=headers)
        f.write(response.content)
    # 插入数据库
    # http://localhost:8088/images/bingwallpaper/.jpg
    filename_path = 'http://localhost:8080' + filepath[1:]
    if filename.find('，') != -1:
        filename_split = filename.split('，')
        wallpaper = WallPaper(filename_path, filename_split[0], author, filename_split[1])
        save_mysql(wallpaper)
    else:
        # TODO 当没有，时,插入默认值
        wallpaper = WallPaper(filename_path, filename, author, '山西')
        save_mysql(wallpaper)
    time.sleep(random.random() * 3)


def parse(html):  # 解析网页源代码
    pattern = re.compile('data-progressive="(.*?)".*?<h3>(.*?)</h3>')  # 正则表达式筛选html
    items = re.findall(pattern, html)
    for item in items:
        try:
            url = item[0].replace('640', '1920').replace('480', '1080')  # 替换图片尺寸
            # print(url)
            imagename = item[1].strip()
            # 提取作者信息
            author = get_author(imagename)
            rule = re.compile(r'[a-zA-z1-9(© )-/]')  # []用来表示一组字符【abc】匹配a,b,或c
            imagename = rule.sub('', imagename)
            download(url, imagename, author)
            print(imagename + author, "正在下载")
        except Exception:
            continue


def get_author(image_detail):
    author_brackets = re.findall(r'[(](.*?)[)]', image_detail)
    author = str(author_brackets[0]).split('© ')[1]
    return author


def save_mysql(wallpaper):
    db = pymysql.connect(host='localhost', user='root', password='ssx14233', port=3306, db='wallpaper')
    cursor = db.cursor()
    sql = "INSERT INTO wallpaper(image_url,image_name,author,address) values (%s,%s,%s,%s)"
    try:
        cursor.execute(sql, (wallpaper.image_url,
                             wallpaper.image_name,
                             wallpaper.author,
                             wallpaper.address,
                             ))
        db.commit()
        print('插入数据成功')
    except:
        db.rollback()
        print("插入数据失败")
    db.close()


if __name__ == '__main__':
    for page in range(1, 20):  # 爬取页面的范围，可以随意更改
        url = 'https://bing.ioliu.cn/?p=' + str(page)
        print("正在抓取第", page, "页", url)
        html = get_one_page(url)
        parse(html)
