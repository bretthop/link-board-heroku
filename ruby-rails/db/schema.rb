# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20130313215859) do

  create_table "link", :force => true do |t|
    t.integer "link_group_id", :null => false
    t.text    "title",         :null => false
    t.text    "href",          :null => false
    t.text    "description"
  end

  create_table "link_group", :force => true do |t|
    t.integer "user_account_id", :null => false
    t.text    "title",           :null => false
    t.text    "description"
  end

  create_table "link_groups", :force => true do |t|
    t.integer  "user_account_id", :null => false
    t.string   "title",           :null => false
    t.string   "description"
    t.datetime "created_at",      :null => false
    t.datetime "updated_at",      :null => false
  end

  create_table "links", :force => true do |t|
    t.integer  "link_group_id", :null => false
    t.string   "title",         :null => false
    t.string   "href",          :null => false
    t.string   "description"
    t.datetime "created_at",    :null => false
    t.datetime "updated_at",    :null => false
  end

  create_table "user_account", :force => true do |t|
    t.text "username",   :null => false
    t.text "password",   :null => false
    t.text "email"
    t.text "first_name"
    t.text "last_name"
  end

  create_table "user_accounts", :force => true do |t|
    t.string   "username",   :null => false
    t.string   "password",   :null => false
    t.string   "email"
    t.string   "first_name"
    t.string   "last_name"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

end
