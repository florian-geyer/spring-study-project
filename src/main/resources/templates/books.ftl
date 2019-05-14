<#import "common_parts/common.ftl" as c>

<@c.page>
<h2>Поиск по названию книги</h2>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/books" class="form-inline">
            <input type="text" name="filter" class="form-control"
                   value="${filter?ifExists}" placeholder="поиск по названию">
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>
</div>



<a class="btn btn-primary my-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    <h3>Добавить книги</h3>
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group ">
                <input type="text" name="title" class="form-control" placeholder="Введите название книги" /><br>
            </div>
            <div class="form-group">
                <input type="text" name="author" class="form-control" placeholder="Введите имя автора" ><br>
            </div>
            <div class="form-group">
                <input type="text" name="genre" class="form-control" placeholder="Жанр книги" ><br>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Добавить изображение</label>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить книгу</button>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" /><br>
        </form>
    </div>
<br><br>
</div>

<h2>Список книг</h2><br>
<div class="card-columns">
    <#list books as book>
        <div class="card my-3">
                <#if book.filename??>
                    <img src="/img/${book.filename}" class="card-img-top">
                </#if>
            <div class="card-footer text-muted">
                <strong>${book.getAuthor_SignatureName()}</strong>
            </div>
            <div class="m2">
                <span><strong>Название: ${book.title}</strong></span>
                <span>Автор: ${book.author}</span>
                <span>Жанр/область: ${book.genre}</span><br>
            </div>
        </div>
    <#else>
        Таких книг в базе данных пока нет
    </#list>
</div>
</@c.page>